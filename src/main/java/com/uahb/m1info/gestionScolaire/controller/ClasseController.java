package com.uahb.m1info.gestionScolaire.controller;

import com.uahb.m1info.gestionScolaire.model.Classe;
import com.uahb.m1info.gestionScolaire.model.Etudiant;
import com.uahb.m1info.gestionScolaire.model.Inscription;
import com.uahb.m1info.gestionScolaire.repository.ClasseRepository;
import com.uahb.m1info.gestionScolaire.repository.FiliereRepository;
import com.uahb.m1info.gestionScolaire.service.IClasse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/classe")
public class ClasseController {
    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private IClasse iClasse;


    /*@PostMapping("/add")
    @ResponseBody
    public Classe save(@RequestBody Classe classe){

        return iClasse.save(classe);
    }*/

    @PostMapping("/save")
    public ResponseEntity<?> saveClasses(@RequestBody Classe classe) {
        return new ResponseEntity<>(classeRepository.save(iClasse.save(classe)), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Classe> list(){
        return iClasse.findAll();
    }

}
