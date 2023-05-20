package com.uahb.m1info.gestionScolaire.service;

import com.uahb.m1info.gestionScolaire.model.Classe;
import com.uahb.m1info.gestionScolaire.model.Etudiant;
import com.uahb.m1info.gestionScolaire.model.Inscription;
import com.uahb.m1info.gestionScolaire.repository.ClasseRepository;
import com.uahb.m1info.gestionScolaire.repository.EtudiantRepository;
import com.uahb.m1info.gestionScolaire.repository.FiliereRepository;
import com.uahb.m1info.gestionScolaire.repository.InscriptionRepository;
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
    @Autowired
    private InscriptionRepository inscriptionRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public Classe save(Classe classe) {
        classe.setFiliere(filiereRepository.findById(classe.getFiliere().getId()).get());
        return classeRepository.save(classe);
    }
    @Override
    public Classe find(Long classe_id) {
        Optional<Classe> classe = classeRepository.findById(classe_id);
        if(classe.isPresent())
            return  classe.get();
        return null;
    }

    @Override
    public List<Classe> findAll() {
        return classeRepository.findAll();
    }


}
