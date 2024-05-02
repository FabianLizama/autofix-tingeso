package com.example.autofix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.autofix.entities.RepairHistoryEntity;

@Repository
public interface RepairHistoryRepository extends JpaRepository<RepairHistoryEntity, Long>{
    @Query(value="SELECT car_id, COUNT(*) AS total_repairs "
    + "FROM repair_history "
    + "WHERE car_id = :car_id "
    + "GROUP BY car_id;", nativeQuery = true)
    public int getTotalRepairsByCarId(Long car_id);
}