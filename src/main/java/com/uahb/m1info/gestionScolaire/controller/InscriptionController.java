package com.uahb.m1info.gestionScolaire.controller;

import com.uahb.m1info.gestionScolaire.model.Classe;
import com.uahb.m1info.gestionScolaire.model.Inscription;
import com.uahb.m1info.gestionScolaire.repository.ClasseRepository;
import com.uahb.m1info.gestionScolaire.repository.InscriptionRepository;
import com.uahb.m1info.gestionScolaire.service.IInscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/inscription")
public class InscriptionController {
    @Autowired
    private IInscription inscriptionService;
    @Autowired
    private InscriptionRepository inscriptionRepository;

    @GetMapping
    @ResponseBody
    public List <Inscription> get(){
        return inscriptionService.find();
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveInscriptions(@RequestBody Inscription inscription) {
        return new ResponseEntity<>(inscriptionRepository.save(inscriptionService.save(inscription)), HttpStatus.CREATED);
    }

    @GetMapping("/list/{classe_id}/{annee}")
    @ResponseBody
    public List<Inscription> findInscriptions(@PathVariable Long classe_id, @PathVariable String annee){
        return inscriptionService.findAll(classe_id, annee);
    }

    @GetMapping("/classes/{classe_id}/{annee}")
    @ResponseBody
    public List<Integer> classesEtAnnee(@PathVariable Long classe_id, @PathVariable String annee) {
        List<Inscription> inscriptions = inscriptionService.find();
        List<Inscription> inscriptionList = new ArrayList<>();
        List<Integer> classes = new ArrayList<>();

        for (Inscription inscription : inscriptions) {
            if (inscription.getClasse().getId() == classe_id && inscription.getAnnee_academic()== annee) {
                inscriptionList.add(inscription);
            }
        }

        for (Inscription inscription : inscriptionList) {
           classes.add(inscription.getClasse().getMensualite());
        }
        return classes;
    }

}
