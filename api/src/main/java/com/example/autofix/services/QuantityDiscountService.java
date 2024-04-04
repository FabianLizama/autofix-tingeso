package com.example.autofix.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.autofix.entities.QuantityDiscountEntity;
import com.example.autofix.repositories.QuantityDiscountRepository;

@Service
public class QuantityDiscountService {
    @Autowired
    QuantityDiscountRepository quantityDiscountRepository;

    // Create
    public QuantityDiscountEntity saveQuantityDiscount(QuantityDiscountEntity quantityDiscount) {
        return quantityDiscountRepository.save(quantityDiscount);
    }

    // Read
    public ArrayList<QuantityDiscountEntity> getAllQuantityDiscounts() {
        return (ArrayList<QuantityDiscountEntity>) quantityDiscountRepository.findAll();
    }

    public QuantityDiscountEntity getQuantityDiscountById(Long id) {
        return quantityDiscountRepository.findById(id).orElse(null);
    }

    // Update
    public QuantityDiscountEntity updateQuantityDiscount(QuantityDiscountEntity quantityDiscount) throws Exception{
        try{
            QuantityDiscountEntity quantityDiscountUpdated = quantityDiscountRepository.save(quantityDiscount);
            return quantityDiscountUpdated;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }   
    }
    
    // Delete
    public void deleteQuantityDiscount(Long id) throws Exception {
        try{
            quantityDiscountRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
