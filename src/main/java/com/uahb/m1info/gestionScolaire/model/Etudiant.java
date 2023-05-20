package com.uahb.m1info.gestionScolaire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, nullable = false)
    private String nom;
    @Column(length = 50, nullable = false)
    private String prenom;
    @Column(length = 20, nullable = false)
    private String matricule;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_naissance;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 50)
    private String adresse;
    @Lob
    private byte[] photo;
    @JsonIgnore
    @OneToMany(mappedBy = "etudiant")
    private List<Inscription> inscriptions;

}
