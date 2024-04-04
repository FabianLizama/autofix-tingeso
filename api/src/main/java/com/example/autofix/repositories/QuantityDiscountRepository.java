package com.example.autofix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.autofix.entities.QuantityDiscountEntity;

@Repository
public interface QuantityDiscountRepository extends JpaRepository<QuantityDiscountEntity, Long>{

}
