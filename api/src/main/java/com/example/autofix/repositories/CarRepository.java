package com.example.autofix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.autofix.entities.CarEntity;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long>{
    CarEntity findByLicensePlate(String licensePlate);
    
}
