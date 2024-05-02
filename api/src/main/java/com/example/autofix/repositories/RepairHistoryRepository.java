package com.example.autofix.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.autofix.entities.RepairHistoryEntity;

@Repository
public interface RepairHistoryRepository extends JpaRepository<RepairHistoryEntity, Long> {
    @Query(value = "SELECT COALESCE((SELECT COUNT(*) FROM repair_history WHERE car_id = :car_id), 0) AS total_repairs", nativeQuery = true)
    public int getTotalRepairsByCarId(Long car_id);

    @Query(value = "SELECT c.license_plate AS licensePlate, r.cost, r.discount, r.rechargues, r.total_amount AS totalAmount, r.repair_type_id AS repairTypeId " +
               "FROM car AS c " +
               "JOIN repair_history AS r ON c.id = r.car_id " +
               "ORDER BY c.license_plate", 
       nativeQuery = true)
    public ArrayList<Object[]> getAllRepairsCosts();

    @Query(value = "SELECT r.name AS repairType, COUNT(DISTINCT c.type) AS numberOfVehicleTypes, SUM(rh.total_amount) AS totalAmount " +
                   "FROM repair r " +
                   "JOIN repair_history rh ON r.id = rh.repair_type_id " +
                   "JOIN car c ON rh.car_id = c.id " +
                   "GROUP BY r.id, r.name " +
                   "ORDER BY SUM(rh.total_amount) DESC", 
           nativeQuery = true)
    public ArrayList<Object[]> getReport2();
    
    @Query(value = "SELECT c.brand, AVG(TIMESTAMPDIFF(SECOND, r.admission_date_time, r.end_date_time)) / 3600.0 AS avg_repair_time_hours " +
               "FROM repair_history r " +
               "JOIN car c ON r.car_id = c.id " +
               "GROUP BY c.brand " +
               "ORDER BY avg_repair_time_hours DESC", nativeQuery = true)
    public ArrayList<Object[]> getReport3();

    @Query(value = "SELECT r.name, " +
               "SUM(CASE WHEN c.motor_type = 'gasoline' THEN 1 ELSE 0 END) AS gasoline_vehicles, " +
               "SUM(CASE WHEN c.motor_type = 'diesel' THEN 1 ELSE 0 END) AS diesel_vehicles, " +
               "SUM(CASE WHEN c.motor_type = 'hybrid' THEN 1 ELSE 0 END) AS hybrid_vehicles, " +
               "SUM(CASE WHEN c.motor_type = 'electric' THEN 1 ELSE 0 END) AS electric_vehicles, " +
               "SUM(rh.cost + rh.rechargues - rh.discount) AS total_amount " +
               "FROM repair r " +
               "JOIN repair_history rh ON rh.repair_type_id = r.id " +
               "JOIN car c ON rh.car_id = c.id " +
               "GROUP BY r.name " +
               "ORDER BY total_amount DESC", nativeQuery = true)
    public ArrayList<Object[]> getReport4();
}
