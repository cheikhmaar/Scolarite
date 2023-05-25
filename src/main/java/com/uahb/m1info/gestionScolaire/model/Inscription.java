package com.uahb.m1info.gestionScolaire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Inscription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_inscription;
    @Column(length = 10, nullable = false)
    private String annee_academic;
    @Column (length = 10, nullable = false)
    private int versement;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;
    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;

    public Inscription() {
        etudiant=new Etudiant();
        classe=new Classe();
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(Date date_inscription) {
        this.date_inscription = date_inscription;
    }

    public String getAnnee_academic() {
        return annee_academic;
    }

    public void setAnnee_academic(String annee_academic) {
        this.annee_academic = annee_academic;
    }

    public int getVersement() {
        return versement;
    }

    public void setVersement(int versement) {
        this.versement = versement;
    }
}
