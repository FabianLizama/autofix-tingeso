package com.example.autofix.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.autofix.entities.ChargueByTypeEntity;

@Repository
public interface ChargueByTypeRepository extends JpaRepository<ChargueByTypeEntity, Long>{
    ArrayList<ChargueByTypeEntity> findBySurchargeType(String surchargeType);
}