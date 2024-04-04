package com.example.autofix.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.autofix.entities.RepairHistoryEntity;
import com.example.autofix.services.RepairHistoryService;

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
@RequestMapping("/api/v1/repair-history")
@CrossOrigin("*") // Cambiar para PRODUCCION
public class RepairHistoryController {
    @Autowired
    RepairHistoryService repairHistoryService;

    // Create
    @PostMapping("/")
    public ResponseEntity<RepairHistoryEntity> saveRepairHistory(@RequestBody RepairHistoryEntity repairHistory) {
        RepairHistoryEntity repairHistoryNew = repairHistoryService.saveRepairHistory(repairHistory);
        return ResponseEntity.ok(repairHistoryNew);
    }

    // Read
    @GetMapping("/")
    public ResponseEntity<ArrayList<RepairHistoryEntity>> getAllRepairHistorys() {
        return ResponseEntity.ok(repairHistoryService.getAllRepairHistories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairHistoryEntity> getRepairHistoryById(@PathVariable Long id) {
        return ResponseEntity.ok(repairHistoryService.getRepairHistoryById(id));
    }

    // Update
    @PutMapping("/")
    public ResponseEntity<RepairHistoryEntity> updateRepairHistory(@RequestBody RepairHistoryEntity repairHistory) throws Exception{
        try {
            RepairHistoryEntity repairHistoryUpdated = repairHistoryService.updateRepairHistory(repairHistory);
            return ResponseEntity.ok(repairHistoryUpdated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRepairHistory(@PathVariable Long id) throws Exception {
        try {
            repairHistoryService.deleteRepairHistory(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
