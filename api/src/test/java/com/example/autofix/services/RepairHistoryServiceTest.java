package com.example.autofix.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.autofix.entities.RepairHistoryEntity;
import com.example.autofix.repositories.RepairHistoryRepository;

public class RepairHistoryServiceTest {
    
    @Mock
    private RepairHistoryRepository repairHistoryRepository;

    @InjectMocks
    private RepairHistoryService repairHistoryService;

    private RepairHistoryEntity repairHistory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        
        repairHistory = new RepairHistoryEntity();
        repairHistory.setId(1L);
        repairHistory.setAdmissionDateTime(LocalDateTime.of(2024, 10, 10, 10, 0, 0));
        repairHistory.setRepairTypeId(1L);
        repairHistory.setCost(100L);
        repairHistory.setEndDateTime(LocalDateTime.of(2024, 10, 10, 11, 0, 0));
        repairHistory.setDeliveryDateTime(LocalDateTime.of(2024, 10, 10, 12, 0, 0));
        repairHistory.setCarId(1L);
        repairHistory.setRechargues(0L);
        repairHistory.setDiscount(0L);
        repairHistory.setTotalAmount(100L);
        when(repairHistoryRepository.save(any(RepairHistoryEntity.class))).thenReturn(repairHistory);
    }

    @Test
    public void testSaveRepairHistory() {
        // When
        RepairHistoryEntity savedRepairHistory = repairHistoryService.saveRepairHistory(repairHistory);
        // Then
        assertThat(savedRepairHistory).isEqualTo(repairHistory);
    }

    @Test
    public void testGetAllRepairHistories() {
        // Given
        ArrayList<RepairHistoryEntity> repairHistories = new ArrayList<>();
        repairHistories.add(repairHistory);
        when(repairHistoryRepository.findAll()).thenReturn(repairHistories);
        // When
        ArrayList<RepairHistoryEntity> foundRepairHistories = repairHistoryService.getAllRepairHistories();
        // Then
        assertThat(foundRepairHistories).isEqualTo(repairHistories);
    }

    @Test
    public void testGetRepairHistoryById() {
        // Given
        when(repairHistoryRepository.findById(1L)).thenReturn(java.util.Optional.of(repairHistory));
        
        // When
        RepairHistoryEntity foundRepairHistory = repairHistoryService.getRepairHistoryById(1L);

        // Then
        assertThat(foundRepairHistory).isEqualTo(repairHistory);
    }

    @Test
    public void testUpdateRepairHistory() throws Exception{
        // Given
        when(repairHistoryRepository.save(any(RepairHistoryEntity.class))).thenReturn(repairHistory);
        
        // When
        RepairHistoryEntity updatedRepairHistory = repairHistoryService.updateRepairHistory(repairHistory);

        // Then
        assertThat(updatedRepairHistory).isEqualTo(repairHistory);
    }

    @Test
    public void testUpdateRepairHistoryException() throws Exception{
        // Given
        doThrow(new RuntimeException()).when(repairHistoryRepository).save(repairHistory);
        
        // When
        try {
            repairHistoryService.updateRepairHistory(repairHistory);
        } catch (Exception e) {
            // Then
            assertThat(e).isInstanceOf(Exception.class);
        }
    }

    @Test
    public void testDeleteRepairHistory() throws Exception {
        // When
        repairHistoryService.deleteRepairHistory(1L);
    }

    @Test
    public void testDeleteRepairHistoryException() {
        // Given
        doThrow(new RuntimeException()).when(repairHistoryRepository).deleteById(1L);        
        // When
        try {
            repairHistoryService.deleteRepairHistory(1L);
        } catch (Exception e) {
            // Then
            assertThat(e).isInstanceOf(Exception.class);
        }
    }

}
