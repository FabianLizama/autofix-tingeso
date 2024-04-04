package com.example.autofix.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.autofix.entities.CarEntity;
import com.example.autofix.repositories.CarRepository;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    // Create
    public CarEntity saveCar(CarEntity car) {
        return carRepository.save(car);
    }

    // Read
    public ArrayList<CarEntity> getAllCars() {
        return (ArrayList<CarEntity>) carRepository.findAll();
    }

    public CarEntity getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public CarEntity getCarByLicensePlate(String licensePlate) {
        return carRepository.findByLicensePlate(licensePlate);
    }

    // Update
    public CarEntity updateCar(CarEntity car) throws Exception{
        try{
            CarEntity carUpdated = carRepository.save(car);
            return carUpdated;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    // Delete
    public void deleteCar(Long id) throws Exception {
        try{
            carRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
