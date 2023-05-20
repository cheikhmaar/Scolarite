package com.uahb.m1info.gestionScolaire.repository;

import com.uahb.m1info.gestionScolaire.model.Classe;
import com.uahb.m1info.gestionScolaire.model.Etudiant;
import com.uahb.m1info.gestionScolaire.model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription,Long> {
    @Query("select i from Inscription i join i.classe c where c.id=:classe_id AND i.annee_academic=:annee")
    List<Inscription> findEtudiantParClasseEtAnnee(Long classe_id, String annee);
    //List<Inscription> findByClasseAndAnnee(Classe classe, String annee_academic);
}
