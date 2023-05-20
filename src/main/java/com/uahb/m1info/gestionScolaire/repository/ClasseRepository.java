package com.uahb.m1info.gestionScolaire.repository;

import com.uahb.m1info.gestionScolaire.model.Classe;
import com.uahb.m1info.gestionScolaire.model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClasseRepository extends JpaRepository<Classe,Long> {
    //Optional<Classe> findById(Long id);
}
