package com.example.autofix.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.autofix.entities.RepairPriceEntity;
import com.example.autofix.services.RepairPriceService;

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
@RequestMapping("/api/v1/repair-price")
@CrossOrigin("*") // Cambiar para PRODUCCION
public class RepairPriceController {
    @Autowired
    RepairPriceService repairPriceService;

    // Create
    @PostMapping("/")
    public ResponseEntity<RepairPriceEntity> saveRepairPrice(@RequestBody RepairPriceEntity repairPrice) {
        RepairPriceEntity repairPriceNew = repairPriceService.saveRepairPrice(repairPrice);
        return ResponseEntity.ok(repairPriceNew);
    }

    // Read
    @GetMapping("/")
    public ResponseEntity<ArrayList<RepairPriceEntity>> getAllRepairPrices() {
        return ResponseEntity.ok(repairPriceService.getAllRepairPrices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairPriceEntity> getRepairPriceById(@PathVariable Long id) {
        return ResponseEntity.ok(repairPriceService.getRepairPriceById(id));
    }

    // Update
    @PutMapping("/")
    public ResponseEntity<RepairPriceEntity> updateRepairPrice(@RequestBody RepairPriceEntity repairPrice) throws Exception{
        try {
            RepairPriceEntity repairPriceUpdated = repairPriceService.updateRepairPrice(repairPrice);
            return ResponseEntity.ok(repairPriceUpdated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRepairPrice(@PathVariable Long id) throws Exception {
        try {
            repairPriceService.deleteRepairPrice(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
