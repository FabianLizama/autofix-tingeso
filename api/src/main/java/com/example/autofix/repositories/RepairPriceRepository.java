package com.example.autofix.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.autofix.entities.RepairPriceEntity;

@Repository
public interface RepairPriceRepository extends JpaRepository<RepairPriceEntity, Long>{

}
