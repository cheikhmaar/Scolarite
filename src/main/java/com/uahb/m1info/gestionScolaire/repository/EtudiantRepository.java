package com.uahb.m1info.gestionScolaire.repository;

import com.uahb.m1info.gestionScolaire.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    //Optional<Etudiant> findByMatricule(String matricule);
}
