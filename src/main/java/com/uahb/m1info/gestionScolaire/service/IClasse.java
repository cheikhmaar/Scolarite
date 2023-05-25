package com.uahb.m1info.gestionScolaire.service;

import com.uahb.m1info.gestionScolaire.model.Classe;

import java.util.List;

public interface IClasse {
    public Classe save(Classe classe);
    public List<Classe> findAll();

}
