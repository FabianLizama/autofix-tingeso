package com.example.autofix.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "quantity_discount")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityDiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private int numRepairsStart;
    private int numRepairsEnd;
    private double gasPercent;
    private double dieselPercent;
    private double hybridPercent;
    private double electricPercent;
}
