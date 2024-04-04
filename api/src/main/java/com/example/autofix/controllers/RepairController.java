package com.example.autofix.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.autofix.entities.RepairEntity;
import com.example.autofix.services.RepairService;

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
@RequestMapping("/api/v1/repair")
@CrossOrigin("*") // Cambiar para PRODUCCION
public class RepairController {
    @Autowired
    RepairService repairService;

    // Create
    @PostMapping("/")
    public ResponseEntity<RepairEntity> saveRepair(@RequestBody RepairEntity repair) {
        RepairEntity repairNew = repairService.saveRepair(repair);
        return ResponseEntity.ok(repairNew);
    }

    // Read
    @GetMapping("/")
    public ResponseEntity<ArrayList<RepairEntity>> getAllRepairs() {
        return ResponseEntity.ok(repairService.getAllRepairs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairEntity> getRepairById(@PathVariable Long id) {
        return ResponseEntity.ok(repairService.getRepairById(id));
    }

    // Update
    @PutMapping("/")
    public ResponseEntity<RepairEntity> updateRepair(@RequestBody RepairEntity repair) throws Exception{
        try {
            RepairEntity repairUpdated = repairService.updateRepair(repair);
            return ResponseEntity.ok(repairUpdated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRepair(@PathVariable Long id) throws Exception {
        try {
            repairService.deleteRepair(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
