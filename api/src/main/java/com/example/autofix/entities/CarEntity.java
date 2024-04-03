package com.example.autofix.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "car")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String licensePlate;
    private String brand;
    private String model;
    private String type;
    private int fabYear;
    private String motorType;
    private int numSeats;
    private String rut;
    private String nameOwner;
}
