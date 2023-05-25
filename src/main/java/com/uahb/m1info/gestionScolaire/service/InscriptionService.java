package com.uahb.m1info.gestionScolaire.service;

import com.uahb.m1info.gestionScolaire.exception.*;
import com.uahb.m1info.gestionScolaire.model.Inscription;
import com.uahb.m1info.gestionScolaire.repository.EtudiantRepository;
import com.uahb.m1info.gestionScolaire.repository.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


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

    @Override
    public Inscription save(Inscription inscription) {
        // Vérification de l'inscription à partir de la matricule
        /*Optional<Etudiant> existingEtudiant = etudiantRepository.findByMatricule(inscription.getEtudiant().getMatricule());
        if (existingEtudiant.isPresent()) {
                inscription.setEtudiant(existingEtudiant.get());
                throw new RuntimeException("L'étudiant avec le matricule " + inscription.getEtudiant().getMatricule() + " existe déjà");
        }*/

        // Vérification du l'age de l'etudiant
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

        /*// Enregistrement de la photo
        MultipartFile photoFile = inscription.getEtudiant().getPhotoFile();
        if (photoFile != null && !photoFile.isEmpty()) {
            try {
                byte[] photo = photoFile.getBytes();
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

        // Extraction des années de début et de fin
        String[] annees = anneeAcademic.split("-");
        int anneeDebut = Integer.parseInt(annees[0]);
        int anneeFin = Integer.parseInt(annees[1]);

        // Vérification de l'intervalle de temps
        if (anneeFin - anneeDebut != 1) {
            throw new AnneeAcademiqueNotFound("L'intervalle de temps de l'année académique doit être d'un an");
        }

        // Validation du nom qui contient des lettres et espaces
        String nom = inscription.getEtudiant().getNom();
        // Convertir la première lettre du nom en majuscule
        nom = nom.substring(0, 1).toUpperCase() + nom.substring(1);
        // Mettre à jour les valeurs dans l'objet "Etudiant"
        String regex = "^[a-zA-Z\\s]+$";
        if (!nom.matches(regex)) {
            throw new NomPrenomNotTelFoundException("Le nom ne doit contenir que des lettres et des espaces");
        }

        // Validation du prenom qui contient des lettres et espaces
        String prenom = inscription.getEtudiant().getPrenom();
        // Convertir la première lettre du prénom en majuscule
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        String regex1 = "^[a-zA-Z\\s]+$";
        if (!prenom.matches(regex1)) {
            throw new NomPrenomNotTelFoundException("Le prénom ne doit contenir que des lettres et des espaces");
        }

        // Validation du tel qui ne contient pas des lettres
        String tel = inscription.getEtudiant().getTel();
        String regex2 = "^(\\+221|0)([ \\-_/]*)(\\d[ \\-_/]*){9}$";
        if (!tel.matches(regex2)) {
            throw new NomPrenomNotTelFoundException("Le numéro de téléphone ne doit contenir que des chiffres");
        }

        // Convertir la première lettre du prénom en majuscule
        String adresse = inscription.getEtudiant().getAdresse();
        adresse = adresse.substring(0, 1).toUpperCase() + adresse.substring(1);
        // Convertir la première lettre du libelle en majuscule
        String libelle = inscription.getClasse().getLibelle();
        libelle = libelle.substring(0, 1).toUpperCase() + libelle.substring(1);

        // Mettre à jour les valeurs dans l'objet "Etudiant"
        inscription.getEtudiant().setNom(nom);
        inscription.getEtudiant().setPrenom(prenom);
        inscription.getEtudiant().setAdresse(adresse);
        inscription.getClasse().setLibelle(libelle);
        return inscriptionRepository.save(inscription);
    }

    @Override
    public List<Inscription> find() {
        return inscriptionRepository.findAll();
    }

    @Override
    public List<Inscription> findAll(Long classe_id, String annee) {
        return inscriptionRepository.findEtudiantParClasseEtAnnee(classe_id, annee);
    }

}
