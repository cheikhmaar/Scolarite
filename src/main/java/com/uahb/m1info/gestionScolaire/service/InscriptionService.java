package com.uahb.m1info.gestionScolaire.service;

import com.uahb.m1info.gestionScolaire.exception.*;
import com.uahb.m1info.gestionScolaire.model.Inscription;
import com.uahb.m1info.gestionScolaire.repository.ClasseRepository;
import com.uahb.m1info.gestionScolaire.repository.EtudiantRepository;
import com.uahb.m1info.gestionScolaire.repository.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class InscriptionService implements IInscription {
    @Autowired
    private InscriptionRepository inscriptionRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private ClasseRepository classeRepository;
    @Override
    public Inscription save(Inscription inscription) {
        /*Optional<Etudiant> existingEtudiant = etudiantRepository.findByMatricule(inscription.getEtudiant().getMatricule());
        if (existingEtudiant.isPresent()) {
                inscription.setEtudiant(existingEtudiant.get());
                throw new RuntimeException("L'étudiant avec le matricule " + inscription.getEtudiant().getMatricule() + " existe déjà");
        }*/

        //Vérification du l'age de l'etudiant
        Date taDate = inscription.getEtudiant().getDate_naissance();
        Instant instant = taDate.toInstant();
        LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        int age = Period.between(date, LocalDate.now()).getYears();
        if (age < 16) {
            throw new EtudiantNotFoundException("L'étudiant doit avoir au moins 16 ans pour s'inscrire.");
        }


        // Vérification de l'inscription dans la même classe la même année
        List<Inscription> etudiantParClasseEtAnnee = inscriptionRepository.findEtudiantParClasseEtAnnee(inscription.getClasse().getId(), inscription.getAnnee_academic());
        if (!etudiantParClasseEtAnnee.isEmpty()) {
            throw new InscriptionNotFoundException("Un étudiant est déjà inscrit dans la même classe la même année");
        }

        //Vérifier si montant verser est conforme
        int somme = inscription.getClasse().getMontant_inscription()+inscription.getClasse().getAutre_frais()+inscription.getClasse().getAutre_frais();
        if (inscription.getVersement() < somme) {
            throw new MontantVerseeNotFoundException("Un étudiant doit verser au minimun une "+somme+" pour pouvoir s'inscrire");
        }

        // Enregistrement de la photo
        /*byte[] photo = inscription.getEtudiant().getPhoto();
        if (photo != null) {
            try {
                inscription.getEtudiant().setPhoto(photo);
            } catch (IOException e) {
                throw new RuntimeException("Impossible de lire le fichier de la photo");
            }
        }*/

        // Validation du format de l'année académique
        String anneeAcademic = inscription.getAnnee_academic();
        if (!anneeAcademic.matches("\\d{4}-\\d{4}")) {
            throw new AnneeAcademiqueNotFound("Le format de l'année académique est incorrect");
        }

        return inscriptionRepository.save(inscription);
    }

    @Override
    public List<Inscription>  find() {
        return inscriptionRepository.findAll();
    }

    @Override
    public List<Inscription> findAll(Long classe_id, String annee) {
        return inscriptionRepository.findEtudiantParClasseEtAnnee(classe_id, annee);
    }

}
