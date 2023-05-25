package com.uahb.m1info.gestionScolaire.service;

import com.uahb.m1info.gestionScolaire.model.Classe;
import com.uahb.m1info.gestionScolaire.repository.ClasseRepository;
import com.uahb.m1info.gestionScolaire.repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasseService implements IClasse {
    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private FiliereRepository filiereRepository;

    @Override
    public Classe save(Classe classe) {
        classe.setFiliere(filiereRepository.findById(classe.getFiliere().getId()).get());

        // Convertir la première lettre du libelle en majuscule
        String libelle = classe.getFiliere().getLibelle();
        libelle = libelle.substring(0, 1).toUpperCase() + libelle.substring(1);

        String libelle1 = classe.getLibelle();
        libelle1 = libelle1.substring(0, 1).toUpperCase() + libelle1.substring(1);

        // Mettre à jour les valeurs dans l'objet "Filiere"
        classe.getFiliere().setLibelle(libelle);
        classe.setLibelle(libelle1);

        return classeRepository.save(classe);

    }

    @Override
    public List<Classe> findAll() {
        return classeRepository.findAll();
    }


}
