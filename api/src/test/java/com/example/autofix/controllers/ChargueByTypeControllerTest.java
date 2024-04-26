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

import static org.hamcrest.Matchers.*;



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

    @Test
    public void testGetAllChargueByTypes() throws Exception {
        ChargueByTypeEntity chargueByType1 = new ChargueByTypeEntity(
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
        ChargueByTypeEntity chargueByType2 = new ChargueByTypeEntity(
            2L,
            "km",
            5001,
            12000,
            -1,
            -1,
            0.03,
            0.05,
            0.05,
            0.05,
            0.05
        );

        ChargueByTypeEntity chargueByType3 = new ChargueByTypeEntity(
            3L,
            "antiquity",
            -1,
            -1,
            0,
            5,
            0,
            0,
            0,
            0,
            0
        );

        ChargueByTypeEntity chargueByType4 = new ChargueByTypeEntity(
            4L,
            "antiquity",
            -1,
            -1,
            6,
            10,
            0.05,
            0.05,
            0.07,
            0.07,
            0.07
        );

        ArrayList<ChargueByTypeEntity> chargueByTypes = new ArrayList<>(Arrays.asList(chargueByType1, chargueByType2, chargueByType3, chargueByType4));
        
        given(chargueByTypeService.getAllChargueByTypes()).willReturn(chargueByTypes);
    
        mockMvc.perform(get("/api/v1/chargue-type/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(4)))
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[1].id", is(2)))
            .andExpect(jsonPath("$[2].id", is(3)))
            .andExpect(jsonPath("$[3].id", is(4)));
    }
    // TODO: Add more test methods as needed

}
