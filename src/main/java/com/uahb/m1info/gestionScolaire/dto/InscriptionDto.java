package com.uahb.m1info.gestionScolaire.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InscriptionDto {
    private LocalDate dateInscription;
    private String anneeAcademique;
    private Long etudiantId;
    private Long classeId;
}
