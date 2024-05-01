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

import com.example.autofix.entities.RepairPriceEntity;
import com.example.autofix.repositories.RepairPriceRepository;

public class RepairPriceServiceTest {

    @Mock
    private RepairPriceRepository repairPriceRepository;

    @InjectMocks
    private RepairPriceService repairPriceService;

    private RepairPriceEntity repairPrice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        repairPrice = new RepairPriceEntity();
        repairPrice.setId(1L);
        repairPrice.setRepairTypeId(1L);
        repairPrice.setGasolinePrice(0);
        repairPrice.setDieselPrice(0);
        repairPrice.setHybridPrice(0);
        repairPrice.setElectricPrice(0);

        when(repairPriceRepository.save(any(RepairPriceEntity.class))).thenReturn(repairPrice);
    }

    @Test
    public void testSaveRepairPrice() {
        // When
        RepairPriceEntity savedRepairPrice = repairPriceService.saveRepairPrice(repairPrice);
        // Then
        assertThat(savedRepairPrice).isEqualTo(repairPrice);
    }

    @Test
    public void testGetAllRepairPrices() {
        // Given
        ArrayList<RepairPriceEntity> repairPrices = new ArrayList<>();
        repairPrices.add(repairPrice);
        when(repairPriceRepository.findAll()).thenReturn(repairPrices);

        // When
        ArrayList<RepairPriceEntity> foundRepairPrices = repairPriceService.getAllRepairPrices();

        // Then
        assertThat(foundRepairPrices).isEqualTo(repairPrices);
    }

    @Test
    public void testGetRepairPriceById() {
        // Given
        when(repairPriceRepository.findById(1L)).thenReturn(java.util.Optional.of(repairPrice));

        // When
        RepairPriceEntity foundRepairPrice = repairPriceService.getRepairPriceById(1L);

        // Then
        assertThat(foundRepairPrice).isEqualTo(repairPrice);
    }

    @Test
    public void testUpdateRepairPrice() throws Exception{
        // Given
        RepairPriceEntity updatedRepairPrice = new RepairPriceEntity();
        updatedRepairPrice.setId(1L);
        updatedRepairPrice.setRepairTypeId(1L);
        updatedRepairPrice.setGasolinePrice(10);
        updatedRepairPrice.setDieselPrice(20);
        updatedRepairPrice.setHybridPrice(30);
        updatedRepairPrice.setElectricPrice(40);

        when(repairPriceRepository.save(any(RepairPriceEntity.class))).thenReturn(updatedRepairPrice);

        // When
        RepairPriceEntity savedRepairPrice = repairPriceService.updateRepairPrice(updatedRepairPrice);

        // Then
        assertThat(savedRepairPrice).isEqualTo(updatedRepairPrice);
    }

    @Test
    public void testUpdateRepairPriceException() throws Exception{
        // Given
        when(repairPriceRepository.save(any(RepairPriceEntity.class))).thenThrow(new RuntimeException());

        // When
        try {
            repairPriceService.updateRepairPrice(repairPrice);
        } catch (Exception e) {
            // Then
            assertThat(e).isInstanceOf(Exception.class);
        }
    }

    @Test
    public void testDeleteRepairPrice() throws Exception{
        // When
        repairPriceService.deleteRepairPrice(1L);
    }

    @Test
    public void testDeleteRepairPriceException() {
        // Given
        doThrow(new RuntimeException()).when(repairPriceRepository).deleteById(1L);

        // When
        try {
            repairPriceService.deleteRepairPrice(1L);
        } catch (Exception e) {
            // Then
            assertThat(e).isInstanceOf(Exception.class);
        }
    }
}
