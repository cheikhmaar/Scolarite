package com.uahb.m1info.gestionScolaire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Classe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private String libelle;
    @Column (length = 10)
    private int montant_inscription;
    @Column (length = 10)
    private int mensualite;
    @Column (length = 10)
    private int autre_frais;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;
    @JsonIgnore
    @OneToMany(mappedBy = "classe")
    private List<Inscription> inscriptions;

}
