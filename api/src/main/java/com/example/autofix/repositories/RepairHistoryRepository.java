package com.example.autofix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.autofix.entities.RepairHistoryEntity;

@Repository
public interface RepairHistoryRepository extends JpaRepository<RepairHistoryEntity, Long>{
    @Query(value="SELECT COALESCE((SELECT COUNT(*) FROM repair_history WHERE car_id = :car_id), 0) AS total_repairs", nativeQuery = true)
    public int getTotalRepairsByCarId(Long car_id);
}