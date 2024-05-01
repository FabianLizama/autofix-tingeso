package com.example.autofix.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.autofix.services.RepairPriceService;
import com.example.autofix.entities.RepairPriceEntity;

@WebMvcTest(RepairPriceController.class)
public class RepairPriceControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepairPriceService repairPriceService;

    @Test
    public void testSaveRepairPrice() throws Exception {
        RepairPriceEntity savedRepairPrice = new RepairPriceEntity(
            1L,
            1L,
            100000,
            100000,
            100000,
            100000
        );
        
        given(repairPriceService.saveRepairPrice(Mockito.any(RepairPriceEntity.class))).willReturn(savedRepairPrice);
        
        String repairPriceJSON = """
                {
                    "repairTypeId": 1,
                    "gasolinePrice": 100000,
                    "dieselPrice": 100000,
                    "hybridPrice": 100000,
                    "electricPrice": 100000
                }
                """;

        mockMvc.perform(post("/api/v1/repair-price/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(repairPriceJSON))
            .andExpect(status().isOk());
    }

    @Test
    public void testGetAllRepairPrices() throws Exception {
        RepairPriceEntity repairPrice1 = new RepairPriceEntity(
            1L,
            1L,
            100000,
            100000,
            100000,
            100000
        );

        RepairPriceEntity repairPrice2 = new RepairPriceEntity(
            2L,
            2L,
            200000,
            200000,
            200000,
            200000
        );

        ArrayList<RepairPriceEntity> repairPrices = new ArrayList<>(Arrays.asList(repairPrice1, repairPrice2));

        given(repairPriceService.getAllRepairPrices()).willReturn((ArrayList<RepairPriceEntity>) repairPrices);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/repair-price/"))
            .andExpect(status().isOk())
            .andExpect(content().json("[{}, {}]"));
    }

    @Test
    public void testGetRepairPriceById() throws Exception {
        RepairPriceEntity repairPrice = new RepairPriceEntity(
            1L,
            1L,
            100000,
            100000,
            100000,
            100000
        );

        given(repairPriceService.getRepairPriceById(1L)).willReturn(repairPrice);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/repair-price/1"))
            .andExpect(status().isOk())
            .andExpect(content().json("{}"));
    }

    @Test
    public void testUpdateRepairPrice() throws Exception {
        RepairPriceEntity repairPrice = new RepairPriceEntity(
            1L,
            1L,
            100000,
            100000,
            100000,
            100000
        );

        given(repairPriceService.updateRepairPrice(Mockito.any(RepairPriceEntity.class))).willReturn(repairPrice);

        String repairPriceJSON = """
                {
                    "repairTypeId": 1,
                    "gasolinePrice": 100000,
                    "dieselPrice": 100000,
                    "hybridPrice": 100000,
                    "electricPrice": 100000
                }
                """;

        mockMvc.perform(put("/api/v1/repair-price/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(repairPriceJSON))
            .andExpect(status().isOk());
    }

    @Test
    public void testDeleteRepairPrice() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/repair-price/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteRepairPrice_Exception() throws Exception {
        Mockito.doThrow(new Exception()).when(repairPriceService).deleteRepairPrice(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/repair-price/1"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateRepairPrice_Exception() throws Exception {
        given(repairPriceService.updateRepairPrice(Mockito.any(RepairPriceEntity.class))).willThrow(new Exception());

        String repairPriceJSON = """
                {
                    "repairTypeId": 1,
                    "gasolinePrice": 100000,
                    "dieselPrice": 100000,
                    "hybridPrice": 100000,
                    "electricPrice": 100000
                }
                """;

        mockMvc.perform(put("/api/v1/repair-price/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(repairPriceJSON))
            .andExpect(status().isBadRequest());
    }
}
