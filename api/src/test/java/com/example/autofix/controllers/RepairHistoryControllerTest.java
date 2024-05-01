package com.example.autofix.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.autofix.entities.RepairHistoryEntity;
import com.example.autofix.services.RepairHistoryService;

@WebMvcTest(RepairHistoryController.class)
public class RepairHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepairHistoryService repairHistoryService;

    @Test
    public void testSaveRepairHistory() throws Exception {
        RepairHistoryEntity savedRepairHistory = new RepairHistoryEntity (
            1L,
            LocalDateTime.of(2024, 10, 10, 10, 0, 0),
            1L,
            0L,
            LocalDateTime.of(2024, 10, 10, 10, 0, 0),
            LocalDateTime.of(2024, 10, 10, 10, 0, 0),
            1L,
            0L,
            0L,
            0L
        );

        given(repairHistoryService.saveRepairHistory(Mockito.any(RepairHistoryEntity.class))).willReturn(savedRepairHistory);

        String repairHistoryJSON = """
                {
                    "id": 1,
                    "admissionDateTime": "2024-10-10T10:00:00",
                    "repairTypeId": 1,
                    "cost": 0,
                    "endDateTime": "2024-10-10T10:00:00",
                    "deliveryDateTime": "2024-10-10T10:00:00",
                    "carId": 1,
                    "rechargues": 0,
                    "discount": 0,
                    "totalAmount": 0
                }
                """;

        mockMvc.perform(post("/api/v1/repair-history/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(repairHistoryJSON))
            .andExpect(status().isOk())
            .andExpect(content().json(repairHistoryJSON));
    }
    
    @Test
    public void testGetAllRepairHistories() throws Exception {
        RepairHistoryEntity repairHistory1 = new RepairHistoryEntity(
            1L,
            LocalDateTime.of(2024, 10, 10, 10, 0, 0),
            1L,
            0L,
            LocalDateTime.of(2024, 10, 10, 10, 0, 0),
            LocalDateTime.of(2024, 10, 10, 10, 0, 0),
            1L,
            0L,
            0L,
            0L
        );

        RepairHistoryEntity repairHistory2 = new RepairHistoryEntity(
            2L,
            LocalDateTime.of(2024, 10, 10, 10, 0, 0),
            1L,
            0L,
            LocalDateTime.of(2024, 10, 10, 10, 0, 0),
            LocalDateTime.of(2024, 10, 10, 10, 0, 0),
            1L,
            0L,
            0L,
            0L
        );

        ArrayList<RepairHistoryEntity> repairHistories = new ArrayList<>(Arrays.asList(repairHistory1, repairHistory2));
        
        given(repairHistoryService.getAllRepairHistories()).willReturn(repairHistories);

        mockMvc.perform(get("/api/v1/repair-history/"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetRepairHistoryById() throws Exception {
        RepairHistoryEntity repairHistory = new RepairHistoryEntity(
            1L,
            LocalDateTime.of(2024, 10, 10, 10, 0, 0),
            1L,
            0L,
            LocalDateTime.of(2024, 10, 10, 10, 0, 0),
            LocalDateTime.of(2024, 10, 10, 10, 0, 0),
            1L,
            0L,
            0L,
            0L
        );

        given(repairHistoryService.getRepairHistoryById(1L)).willReturn(repairHistory);

        mockMvc.perform(get("/api/v1/repair-history/1"))
            .andExpect(status().isOk())
            .andExpect(content().json("{}"));
    }

    @Test
    public void testUpdateRepairHistory() throws Exception {
        RepairHistoryEntity updatedRepairHistory = new RepairHistoryEntity(
            1L,
            LocalDateTime.of(2024, 10, 10, 10, 0, 0),
            1L,
            0L,
            LocalDateTime.of(2024, 10, 10, 10, 0, 0),
            LocalDateTime.of(2024, 10, 10, 10, 0, 0),
            1L,
            0L,
            0L,
            0L
        );

        given(repairHistoryService.updateRepairHistory(Mockito.any(RepairHistoryEntity.class))).willReturn(updatedRepairHistory);

        String repairHistoryJSON = """
                {
                    "id": 1,
                    "admissionDateTime": "2024-10-10T10:00:00",
                    "repairTypeId": 1,
                    "cost": 0,
                    "endDateTime": "2024-10-10T10:00:00",
                    "deliveryDateTime": "2024-10-10T10:00:00",
                    "carId": 1,
                    "rechargues": 0,
                    "discount": 0,
                    "totalAmount": 0
                }
                """;

        mockMvc.perform(put("/api/v1/repair-history/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(repairHistoryJSON))
            .andExpect(status().isOk())
            .andExpect(content().json(repairHistoryJSON));
    }

    @Test
    public void testDeleteRepairHistory() throws Exception {
        mockMvc.perform(delete("/api/v1/repair-history/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteRepairHistoryException() throws Exception {
        Mockito.doThrow(new Exception()).when(repairHistoryService).deleteRepairHistory(1L);

        mockMvc.perform(delete("/api/v1/repair-history/1"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateRepairHistoryException() throws Exception {
        Mockito.doThrow(new Exception()).when(repairHistoryService).updateRepairHistory(Mockito.any(RepairHistoryEntity.class));

        String repairHistoryJSON = """
                {
                    "id": 1,
                    "admissionDateTime": "2024-10-10T10:00:00",
                    "repairTypeId": 1,
                    "cost": 0,
                    "endDateTime": "2024-10-10T10:00:00",
                    "deliveryDateTime": "2024-10-10T10:00:00",
                    "carId": 1,
                    "rechargues": 0,
                    "discount": 0,
                    "totalAmount": 0
                }
                """;

        mockMvc.perform(put("/api/v1/repair-history/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(repairHistoryJSON))
            .andExpect(status().isBadRequest());
        
    }
}
