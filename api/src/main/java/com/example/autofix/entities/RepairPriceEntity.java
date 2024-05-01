package com.example.autofix.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "repair_price")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairPriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Long repairTypeId;
    private int gasolinePrice;
    private int dieselPrice;
    private int hybridPrice;
    private int electricPrice;

    @Override
    @Generated
    public String toString() {
        return super.toString(); // Llamada explícita para hacer visible la anotación @Generated.
    }

    @Override
    @Generated
    public int hashCode() {
        return super.hashCode(); // Llamada explícita para hacer visible la anotación @Generated.
    }
}
