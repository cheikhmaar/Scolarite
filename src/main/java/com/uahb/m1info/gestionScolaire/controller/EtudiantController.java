package com.uahb.m1info.gestionScolaire.controller;

import com.uahb.m1info.gestionScolaire.model.Etudiant;
import com.uahb.m1info.gestionScolaire.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiant")
public class EtudiantController {
    @Autowired
    private EtudiantRepository etudiantRepository;

    /*@PostMapping("/add")
    @ResponseBody
    public Etudiant save(@RequestBody Etudiant etudiant){
        return etudiantService.add(etudiant);
    }*/

    @GetMapping("/list")
    @ResponseBody
    public List<Etudiant> findEtudiants(){
        return etudiantRepository.findAll();
    }
}
