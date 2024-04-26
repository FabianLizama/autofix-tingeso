package com.example.autofix.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "chargue_by_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargueByTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String surchargeType; // Km o Antiquity
    // If km
    private int kmStart;
    private int kmEnd;
    
    //If Antiquity
    private int antiqStart;
    private int antiqEnd;

    private double sedanPercent;
    private double hatchPercent;
    private double suvPercent;
    private double pickupPercent;
    private double vanPercent;

}
