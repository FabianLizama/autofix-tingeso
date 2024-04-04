package com.example.autofix.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.autofix.entities.ChargueByTypeEntity;
import com.example.autofix.services.ChargueByTypeService;

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
@RequestMapping("/api/v1/chargue-type")
@CrossOrigin("*") // Cambiar para PRODUCCION
public class ChargueByTypeController {
    @Autowired
    ChargueByTypeService chargueByTypeService;

    // Create
    @PostMapping("/")
    public ResponseEntity<ChargueByTypeEntity> saveChargueByType(@RequestBody ChargueByTypeEntity chargueByType) {
        ChargueByTypeEntity chargueByTypeNew = chargueByTypeService.saveChargueByType(chargueByType);
        return ResponseEntity.ok(chargueByTypeNew);
    }

    // Read
    @GetMapping("/")
    public ResponseEntity<ArrayList<ChargueByTypeEntity>> getAllChargueByTypes() {
        return ResponseEntity.ok(chargueByTypeService.getAllChargueByTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChargueByTypeEntity> getChargueByTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(chargueByTypeService.getChargueByTypeById(id));
    }

    // Update
    @PutMapping("/")
    public ResponseEntity<ChargueByTypeEntity> updateChargueByType(@RequestBody ChargueByTypeEntity chargueByType) throws Exception{
        try {
            ChargueByTypeEntity chargueByTypeUpdated = chargueByTypeService.updateChargueByType(chargueByType);
            return ResponseEntity.ok(chargueByTypeUpdated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteChargueByType(@PathVariable Long id) throws Exception {
        try {
            chargueByTypeService.deleteChargueByType(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
