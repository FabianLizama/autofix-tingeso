package com.example.autofix.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.autofix.entities.ChargueByTypeEntity;

@Repository
public interface ChargueByTypeRepository extends JpaRepository<ChargueByTypeEntity, Long>{
    ArrayList<ChargueByTypeEntity> findBySurchargeType(String surchargeType);
    @Query(value="SELECT CASE :tipo_auto " +
        "WHEN 'sedan' THEN sedan_percent " +
        "WHEN 'pickup' THEN pickup_percent " +
        "WHEN 'suv' THEN suv_percent " +
        "WHEN 'van' THEN van_percent " +
        "WHEN 'hatch' THEN hatch_percent " +
        "ELSE NULL " +
        "END as recargo_percent " +
        "FROM chargue_by_type " +
        "WHERE :km >= km_start " +
        "AND :km <= km_end;", nativeQuery = true)
    public double getChargueKmByType(@Param("km") int km, @Param("tipo_auto") String tipo_auto);
    
    @Query(value="SELECT CASE :tipo_auto " +
        "WHEN 'sedan' THEN sedan_percent " +
        "WHEN 'pickup' THEN pickup_percent " +
        "WHEN 'suv' THEN suv_percent " +
        "WHEN 'van' THEN van_percent " +
        "WHEN 'hatch' THEN hatch_percent " +
        "ELSE NULL " +
        "END as recargo_percent " +
        "FROM chargue_by_type " +
        "WHERE :antiq >= antiq_start " +
        "AND :antiq <= antiq_end;", nativeQuery = true)
    public double getChargueAntiqByType(@Param("antiq") int antiq, @Param("tipo_auto") String tipo_auto);
}