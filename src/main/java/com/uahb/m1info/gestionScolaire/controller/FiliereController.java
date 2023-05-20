package com.uahb.m1info.gestionScolaire.controller;

import com.uahb.m1info.gestionScolaire.model.Filiere;
import com.uahb.m1info.gestionScolaire.repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/filiere")
public class FiliereController {
    @Autowired
    private FiliereRepository filiereRepository;

    @GetMapping
    public List<Filiere> index(){
        return filiereRepository.findAll() ;
    }
}
