package com.example.autofix.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import com.example.autofix.entities.ChargueByTypeEntity;
import com.example.autofix.services.ChargueByTypeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;


@WebMvcTest(ChargueByTypeController.class)
public class ChargueByTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChargueByTypeService chargueByTypeService;

    @Test
    public void testSaveChargueByType() throws Exception {
        ChargueByTypeEntity savedChargueByType = new ChargueByTypeEntity(
            1L,
            "km",
            0,
            5000,
            -1,
            -1,
            0,
            0,
            0,
            0,
            0
        );
        
        given(chargueByTypeService.saveChargueByType(Mockito.any(ChargueByTypeEntity.class))).willReturn(savedChargueByType);
    
        String chargueByTypeJSON = """
                {
                    "surchargeType": "km",
                    "kmStart": 0,
                    "kmEnd": 5000,
                    "antiqStart": 0,
                    "antiqEnd": 0,
                    "sedanPercent": 0,
                    "hatchPercent": 0,
                    "suvPercent": 0,
                    "pickupPercent": 0,
                    "vanPercent": 0
                }
                """;
        
        mockMvc.perform(post("/api/v1/chargue-type/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(chargueByTypeJSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)));
    }

    private ChargueByTypeEntity chargueTestExample = new ChargueByTypeEntity(1L, "km", 0, 5000, -1, -1, 0, 0, 0, 0, 0);

    private ArrayList<ChargueByTypeEntity> chargueByTypes = new ArrayList<>(Arrays.asList(
        new ChargueByTypeEntity(1L, "km", 0, 5000, -1, -1, 0, 0, 0, 0, 0),
        new ChargueByTypeEntity(2L, "km", 5001, 12000, -1, -1, 0.03, 0.05, 0.05, 0.05, 0.05),
        new ChargueByTypeEntity(3L, "antiquity", -1, -1, 0, 5, 0, 0, 0, 0, 0),
        new ChargueByTypeEntity(4L, "antiquity", -1, -1, 6, 10, 0.05, 0.05, 0.07, 0.07, 0.07)
    ));
    
    @Test
    public void testGetAllChargueByTypes() throws Exception {
        
        given(chargueByTypeService.getAllChargueByTypes()).willReturn(chargueByTypes);
    
        mockMvc.perform(get("/api/v1/chargue-type/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(4)))
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[1].id", is(2)))
            .andExpect(jsonPath("$[2].id", is(3)))
            .andExpect(jsonPath("$[3].id", is(4)));
    }

    @Test
    public void testGetAllKmChargueByTypes() throws Exception {

        ArrayList<ChargueByTypeEntity> kmChargueByTypes = chargueByTypes.stream()
                .filter(chargueByType -> chargueByType.getSurchargeType().equals("km"))
                .collect(Collectors.toCollection(ArrayList::new));

        given(chargueByTypeService.getAllKmChargueByTypes()).willReturn(kmChargueByTypes);

        mockMvc.perform(get("/api/v1/chargue-type/km"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetAllAntiqChargueByTypes() throws Exception {
        ArrayList<ChargueByTypeEntity> antiqChargueByTypes = chargueByTypes.stream()
                .filter(chargueByType -> chargueByType.getSurchargeType().equals("antiquity"))
                .collect(Collectors.toCollection(ArrayList::new));

        given(chargueByTypeService.getAllAntiqChargueByTypes()).willReturn(antiqChargueByTypes);

        mockMvc.perform(get("/api/v1/chargue-type/antiq"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetChargueByTypeById() throws Exception {
        Long id = 1L;
        ChargueByTypeEntity chargueByType = new ChargueByTypeEntity(1L, "km", 0, 5000, -1, -1, 0, 0, 0, 0, 0);

        given(chargueByTypeService.getChargueByTypeById(id)).willReturn(chargueByType);

        // Act & Assert
        mockMvc.perform(get("/api/v1/chargue-type/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void testUpdateChargueByType() throws Exception {

        given(chargueByTypeService.updateChargueByType(Mockito.any(ChargueByTypeEntity.class))).willReturn(chargueTestExample);

        String ChargueJSON = """
                {
                    "id": 1,
                    "surchargeType": "km",
                    "kmStart": 0,
                    "kmEnd": 5000,
                    "antiqStart": -1,
                    "antiqEnd": -1,
                    "sedanPercent": 0,
                    "hatchPercent": 0,
                    "suvPercent": 0,
                    "pickupPercent": 0,
                    "vanPercent": 0
                }
                """;

        mockMvc.perform(put("/api/v1/chargue-type/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ChargueJSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateChargueByType_Exception() throws Exception {
        given(chargueByTypeService.updateChargueByType(any(ChargueByTypeEntity.class))).willThrow(new Exception());

        String chargueByTypeJSON = """
                {
                    "id": 1,
                    "surchargeType": "km",
                    "kmStart": 0,
                    "kmEnd": 5000,
                    "antiqStart": -1,
                    "antiqEnd": -1,
                    "sedanPercent": 0,
                    "hatchPercent": 0,
                    "suvPercent": 0,
                    "pickupPercent": 0,
                    "vanPercent": 0
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/chargue-type/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(chargueByTypeJSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testDeleteChargueByType_Exception() throws Exception {
        Long id = 1L;
        doThrow(new Exception("Delete failed")).when(chargueByTypeService).deleteChargueByType(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/chargue-type/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testDeleteChargueByType() throws Exception {
        Long id = 1L;
        
        mockMvc.perform(delete("/api/v1/chargue-type/{id}", id))
                .andExpect(status().isNoContent());
    }
}