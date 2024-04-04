package com.example.autofix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.autofix.entities.RepairHistoryEntity;

@Repository
public interface RepairHistoryRepository extends JpaRepository<RepairHistoryEntity, Long>{

}