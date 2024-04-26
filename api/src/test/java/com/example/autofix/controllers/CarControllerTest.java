package com.example.autofix.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;

import com.example.autofix.entities.CarEntity;
import com.example.autofix.services.CarService;

import org.springframework.http.MediaType;

@WebMvcTest(CarController.class)
public class CarControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    public void testSaveCar() throws Exception{
        CarEntity savedCar = new CarEntity(
            1L,
            "ABC123",
            "Toyota",
            "Corolla",
            "Sedan",
            2021,
            "Gasoline",
            5,
            "123456789",
            "Juan Perez"
        );

        given(carService.saveCar(Mockito.any(CarEntity.class))).willReturn(savedCar);

        String carJSON = """
                {
                    "licensePlate": "ABC123",
                    "brand": "Toyota",
                    "model": "Corolla",
                    "type": "Sedan",
                    "fabYear": 2021,
                    "motorType": "Gasoline",
                    "numSeats": 5,
                    "rut": "123456789",
                    "nameOwner": "Juan Perez"
                }
                """;
        mockMvc.perform(post("/api/v1/cars/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(carJSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.licensePlate", is("ABC123")));
    }


    @Test
    public void testGetAllCars() throws Exception {
        CarEntity car1 = new CarEntity(
            1L,
            "ABC123",
            "Toyota",
            "Corolla",
            "Sedan",
            2021,
            "Gasoline",
            5,
            "123456789",
            "Juan Perez"
        );

        CarEntity car2 = new CarEntity(
            2L,
            "DEF456",
            "Chevrolet",
            "Spark",
            "Hatchback",
            2020,
            "Gasoline",
            5,
            "987654321",
            "Maria Lopez"
        );

        ArrayList<CarEntity> carList = new ArrayList<>(Arrays.asList(car1, car2));

        given(carService.getAllCars()).willReturn((ArrayList<CarEntity>) carList);
    
        mockMvc.perform(get("/api/v1/cars/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].licensePlate", is("ABC123")))
                .andExpect(jsonPath("$[1].licensePlate", is("DEF456")));
    }

    @Test
    public void testGetCarById() throws Exception {
        CarEntity car = new CarEntity(
            1L,
            "ABC123",
            "Toyota",
            "Corolla",
            "Sedan",
            2021,
            "Gasoline",
            5,
            "123456789",
            "Juan Perez"
        );

        given(carService.getCarById(1L)).willReturn(car);

        mockMvc.perform(get("/api/v1/cars/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.licensePlate", is("ABC123")));
    }

    @Test
    public void testUpdateCar() throws Exception{
        CarEntity updatedCar = new CarEntity(
            1L,
            "ABC123",
            "Toyota",
            "Corolla",
            "Sedan",
            2021,
            "Gasoline",
            5,
            "123456789",
            "Juan Perez"
        );

        given(carService.updateCar(Mockito.any(CarEntity.class))).willReturn(updatedCar);

        String carJSON = """
                {
                    "id": 1,
                    "licensePlate": "ABC123",
                    "brand": "Toyota",
                    "model": "Corolla",
                    "type": "Sedan",
                    "fabYear": 2021,
                    "motorType": "Gasoline",
                    "numSeats": 5,
                    "rut": "123456789",
                    "nameOwner": "Juan Perez"
                }
                """;
        
        mockMvc.perform(put("/api/v1/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(carJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.licensePlate", is("ABC123")));
    }

    @Test
    public void testDeleteCar() throws Exception {
        carService.deleteCar(1L);

        mockMvc.perform(delete("/api/v1/cars/1"))
                .andExpect(status().isNoContent());
    }
}
