package com.example.autofix.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.autofix.entities.CarEntity;
import com.example.autofix.repositories.CarRepository;

public class CarServiceTest {
    
    @Mock
    private CarRepository carRepository;
    
    @InjectMocks
    private CarService carService;

    private CarEntity car;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        car = new CarEntity();
        car.setLicensePlate("ABC123");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setType("Sedan");
        car.setFabYear(2021);
        car.setMotorType("Gasoline");
        car.setNumSeats(5);
        car.setRut("123456789");
        car.setNameOwner("Juan Perez");

        when(carRepository.save(any(CarEntity.class))).thenReturn(car);
    }

    @Test
    public void testSaveCar() {
        // When
        CarEntity savedCar = carService.saveCar(car);

        // Then
        assertThat(savedCar).isEqualTo(car);
    }

    @Test
    public void testGetAllCars() {
        // Given
        ArrayList<CarEntity> cars = new ArrayList<>();
        cars.add(car);
        when(carRepository.findAll()).thenReturn(cars);

        // When
        ArrayList<CarEntity> allCars = carService.getAllCars();

        // Then
        assertThat(allCars).isEqualTo(cars);
    }

    @Test
    public void testGetCarById() {
        // Given
        when(carRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(car));

        // When
        CarEntity carById = carService.getCarById(1L);

        // Then
        assertThat(carById).isEqualTo(car);
    }

    @Test
    public void testGetCarByLicensePlate() {
        // Given
        when(carRepository.findByLicensePlate("ABC123")).thenReturn(car);

        // When
        CarEntity carByLicensePlate = carService.getCarByLicensePlate("ABC123");

        // Then
        assertThat(carByLicensePlate).isEqualTo(car);
    }

    @Test
    public void testUpdateCar() throws Exception {
        // Given
        when(carRepository.save(any(CarEntity.class))).thenReturn(car);

        // When
        CarEntity updatedCar = carService.updateCar(car);

        // Then
        assertThat(updatedCar).isEqualTo(car);
    }

    @Test
    public void testDeleteCar() throws Exception {
        // When
        carService.deleteCar(1L);
    }

    @Test
    public void testUpdateCarException() {
        // Given
        when(carRepository.save(any(CarEntity.class))).thenThrow(new RuntimeException());

        // When
        try {
            carService.updateCar(car);
        } catch (Exception e) {
            // Then
            assertThat(e).isInstanceOf(Exception.class);
        }
    }

    @Test
    public void testDeleteCarException() {
        // Given
        doThrow(new RuntimeException()).when(carRepository).deleteById(1L);

        // When
        try {
            carService.deleteCar(1L);
        } catch (Exception e) {
            // Then
            assertThat(e).isInstanceOf(Exception.class);
        }
    }

}

