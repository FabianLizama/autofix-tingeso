package com.example.autofix.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.autofix.entities.CarEntity;
import com.example.autofix.services.CarService;

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
@RequestMapping("/api/v1/cars")
@CrossOrigin("*") // Cambiar para PRODUCCION
public class CarController {
    @Autowired
    CarService carService;

    // Create
    @PostMapping("/")
    public ResponseEntity<CarEntity> saveCar(@RequestBody CarEntity car) {
        CarEntity carNew = carService.saveCar(car);
        return ResponseEntity.ok(carNew);
    }

    // Read
    @GetMapping("/")
    public ResponseEntity<ArrayList<CarEntity>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarEntity> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    // Update
    @PutMapping("/")
    public ResponseEntity<CarEntity> updateCar(@RequestBody CarEntity car) throws Exception{
        try {
            CarEntity carUpdated = carService.updateCar(car);
            return ResponseEntity.ok(carUpdated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCar(@PathVariable Long id) throws Exception {
        try {
            carService.deleteCar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
