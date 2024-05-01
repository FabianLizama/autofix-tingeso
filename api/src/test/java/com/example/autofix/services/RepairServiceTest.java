package com.example.autofix.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.autofix.entities.RepairEntity;
import com.example.autofix.repositories.RepairRepository;

public class RepairServiceTest {
    
    @Mock
    private RepairRepository repairRepository;

    @InjectMocks
    private RepairService repairService;

    private RepairEntity repair;

    @BeforeEach
    public void setup() {
        // This will initialize the mocks and inject them properly
        MockitoAnnotations.openMocks(this);
        
        repair = new RepairEntity();
        repair.setId(1L);
        repair.setName("Reparación");
        repair.setDescription("Descripción");

        when(repairRepository.save(any(RepairEntity.class))).thenReturn(repair);
    }

    @Test
    public void testSaveRepair() {
        // When
        RepairEntity savedRepair = repairService.saveRepair(repair);
        // Then
        assertThat(savedRepair).isEqualTo(repair);
    }
    
    @Test
    public void testGetAllRepairs() {
        // Given
        ArrayList<RepairEntity> repairs = new ArrayList<>();
        repairs.add(repair);
        when(repairRepository.findAll()).thenReturn(repairs);
        // When
        ArrayList<RepairEntity> repairsFound = repairService.getAllRepairs();
        // Then
        assertThat(repairsFound).isEqualTo(repairs);
    }

    @Test
    public void testGetRepairById() {
        // Given
        when(repairRepository.findById(1L)).thenReturn(java.util.Optional.of(repair));
        // When
        RepairEntity repairFound = repairService.getRepairById(1L);
        // Then
        assertThat(repairFound).isEqualTo(repair);
    }

    @Test
    public void testUpdateRepair() throws Exception {
        // Given
        when(repairRepository.save(any(RepairEntity.class))).thenReturn(repair);
        // When
        RepairEntity repairUpdated = repairService.updateRepair(repair);
        // Then
        assertThat(repairUpdated).isEqualTo(repair);
    }

    @Test
    public void testUpdateRepairException () {
        // Given
        when(repairRepository.save(any(RepairEntity.class))).thenThrow(new RuntimeException());
        // When
        try {
            repairService.updateRepair(repair);
        } catch (Exception e) {

            // Then
            assertThat(e).isInstanceOf(Exception.class);
        }
    }

    @Test
    public void testDeleteRepair() throws Exception {
        // When
        repairService.deleteRepair(1L);
    }

    @Test
    public void testDeleteRepairException() {
        // Given
        doThrow(new RuntimeException()).when(repairRepository).deleteById(1L);
        // When
        Exception exception = null;
        try {
            repairService.deleteRepair(1L);
        } catch (Exception e) {
            exception = e;
        }
        // Then
        assertThat(exception).isNotNull();
    }

}
