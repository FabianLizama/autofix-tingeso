package com.example.autofix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.autofix.entities.QuantityDiscountEntity;

@Repository
public interface QuantityDiscountRepository extends JpaRepository<QuantityDiscountEntity, Long>{
    @Query(value="SELECT CASE :motor_type " +
        "WHEN 'electric' THEN electric_percent " +
        "WHEN 'gasoline' THEN gas_percent " +
        "WHEN 'diesel' THEN diesel_percent " +
        "WHEN 'hybrid' THEN hybrid_percent " +
        "ELSE NULL " +
        "END as discount_percent " +
        "FROM quantity_discount " +
        "WHERE (:repair_num >= num_repairs_start AND :repair_num <= num_repairs_end) OR (num_repairs_end = -1 AND :repair_num >= num_repairs_start AND num_repairs_start != -1);", nativeQuery = true)
    public double getDiscountPercent(@Param("repair_num") int repairNum, @Param("motor_type") String motorType);
}
