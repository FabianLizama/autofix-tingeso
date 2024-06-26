package com.example.autofix.entities;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "repair_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairHistoryEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private LocalDateTime admissionDateTime;
    private Long repairTypeId;
    private Long cost;
    private LocalDateTime endDateTime;
    private LocalDateTime deliveryDateTime;
    private Long carId;
    private Long rechargues;
    private Long discount;
    private Long totalAmount;

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
