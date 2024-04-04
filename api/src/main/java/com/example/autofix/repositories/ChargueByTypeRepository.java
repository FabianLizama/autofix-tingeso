package com.example.autofix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.autofix.entities.ChargueByTypeEntity;

@Repository
public interface ChargueByTypeRepository extends JpaRepository<ChargueByTypeEntity, Long>{

}