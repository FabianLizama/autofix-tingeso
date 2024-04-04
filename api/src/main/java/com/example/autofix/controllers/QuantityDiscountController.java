package com.example.autofix.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.autofix.entities.QuantityDiscountEntity;
import com.example.autofix.services.QuantityDiscountService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1/quantity-discount")
@CrossOrigin("*") // Cambiar para PRODUCCION
public class QuantityDiscountController {
    @Autowired
    QuantityDiscountService quantityDiscountService;

    // Create
    @PostMapping("/")
    public ResponseEntity<QuantityDiscountEntity> saveQuantityDiscount(@RequestBody QuantityDiscountEntity quantityDiscount) {
        QuantityDiscountEntity quantityDiscountNew = quantityDiscountService.saveQuantityDiscount(quantityDiscount);
        return ResponseEntity.ok(quantityDiscountNew);
    }

    // Read
    @GetMapping("/")
    public ResponseEntity<ArrayList<QuantityDiscountEntity>> getAllQuantityDiscounts() {
        return ResponseEntity.ok(quantityDiscountService.getAllQuantityDiscounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuantityDiscountEntity> getQuantityDiscountById(@PathVariable Long id) {
        return ResponseEntity.ok(quantityDiscountService.getQuantityDiscountById(id));
    }

    // Update
    @PutMapping("/")
    public ResponseEntity<QuantityDiscountEntity> updateQuantityDiscount(@RequestBody QuantityDiscountEntity quantityDiscount) throws Exception{
        try {
            QuantityDiscountEntity quantityDiscountUpdated = quantityDiscountService.updateQuantityDiscount(quantityDiscount);
            return ResponseEntity.ok(quantityDiscountUpdated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteQuantityDiscount(@PathVariable Long id) throws Exception {
        try {
            quantityDiscountService.deleteQuantityDiscount(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
