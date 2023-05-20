package com.uahb.m1info.gestionScolaire.service;

import com.uahb.m1info.gestionScolaire.model.Classe;
import com.uahb.m1info.gestionScolaire.model.Inscription;

import java.util.List;

public interface IInscription {
    Inscription save(Inscription inscription);
    List<Inscription> find();
    List<Inscription> findAll(Long classe_id, String annee);
}
