package com.example.autofix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.autofix.entities.RepairEntity;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long>{

}
