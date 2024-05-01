package com.example.autofix.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.autofix.entities.RepairEntity;
import com.example.autofix.services.RepairService;


@WebMvcTest(RepairController.class)
public class RepairControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepairService repairService;

    @Test
    public void testSaveRepair() throws Exception {
        RepairEntity savedRepair = new RepairEntity(
            1L,
            "Reparación",
            "Descripción"
        );

        given(repairService.saveRepair(Mockito.any(RepairEntity.class))).willReturn(savedRepair);

        String repairJSON = """
                {
                    "name": "Reparación",
                    "description": "Descripción"
                }
                """;

        mockMvc.perform(post("/api/v1/repair/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(repairJSON))
            .andExpect(status().isOk())
            .andExpect(content().json(repairJSON));

    }

    @Test
    public void testGetAllRepairs() throws Exception {
        RepairEntity repair1 = new RepairEntity(
            1L,
            "Reparación",
            "Descripción"
        );

        RepairEntity repair2 = new RepairEntity(
            2L,
            "Reparación 2",
            "Descripción 2"
        );

        ArrayList<RepairEntity> repairs = new ArrayList<>(Arrays.asList(repair1, repair2));

        given(repairService.getAllRepairs()).willReturn(repairs);

        mockMvc.perform(get("/api/v1/repair/"))
            .andExpect(status().isOk())
            .andExpect(content().json("[{}, {}]"));
    }

    @Test
    public void testGetRepairById() throws Exception {
        RepairEntity repair = new RepairEntity(
            1L,
            "Reparación",
            "Descripción"
        );

        given(repairService.getRepairById(1L)).willReturn(repair);

        mockMvc.perform(get("/api/v1/repair/1"))
            .andExpect(status().isOk())
            .andExpect(content().json("{}"));
    }

    @Test
    public void testUpdateRepair() throws Exception {
        RepairEntity updatedRepair = new RepairEntity(
            1L,
            "Reparación",
            "Descripción"
        );

        given(repairService.updateRepair(Mockito.any(RepairEntity.class))).willReturn(updatedRepair);

        String repairJSON = """
                {
                    "name": "Reparación",
                    "description": "Descripción"
                }
                """;

        mockMvc.perform(put("/api/v1/repair/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(repairJSON))
            .andExpect(status().isOk())
            .andExpect(content().json(repairJSON));
    }

    @Test
    public void testDeleteRepair() throws Exception {
        mockMvc.perform(delete("/api/v1/repair/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteRepairException() throws Exception {
        Mockito.doThrow(new Exception()).when(repairService).deleteRepair(1L);

        mockMvc.perform(delete("/api/v1/repair/1"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateRepairException() throws Exception {
        Mockito.doThrow(new Exception()).when(repairService).updateRepair(Mockito.any(RepairEntity.class));

        String repairJSON = """
                {
                    "name": "Reparación",
                    "description": "Descripción"
                }
                """;

        mockMvc.perform(put("/api/v1/repair/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(repairJSON))
            .andExpect(status().isBadRequest());
    }
}
