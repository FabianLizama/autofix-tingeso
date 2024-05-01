package com.example.autofix.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.autofix.entities.ChargueByTypeEntity;
import com.example.autofix.repositories.ChargueByTypeRepository;

public class ChargueByTypeServiceTest {
    
    @Mock
    private ChargueByTypeRepository chargueByTypeRepository;

    @InjectMocks
    private ChargueByTypeService chargueByTypeService;

    private ChargueByTypeEntity chargueByType;

    @BeforeEach
    public void setup() {
        // This will initialize the mocks and inject them properly
        MockitoAnnotations.openMocks(this);
        
        chargueByType = new ChargueByTypeEntity();
        chargueByType.setId(1L);
        chargueByType.setSurchargeType("km");
        chargueByType.setKmStart(0);
        chargueByType.setKmEnd(1000);
        chargueByType.setSedanPercent(0.1);
        chargueByType.setHatchPercent(0.2);
        chargueByType.setSuvPercent(0.3);
        chargueByType.setPickupPercent(0.4);
        chargueByType.setVanPercent(0.5);

        when(chargueByTypeRepository.save(any(ChargueByTypeEntity.class))).thenReturn(chargueByType);
    }

    @Test
    public void testSaveChargueByType() {
        // When
        ChargueByTypeEntity savedChargueByType = chargueByTypeService.saveChargueByType(chargueByType);
        // Then
        assertThat(savedChargueByType).isEqualTo(chargueByType);
    }

    @Test
    public void testGetAllChargueByTypes() {
        // Given
        ArrayList<ChargueByTypeEntity> chargueByTypes = new ArrayList<>();
        chargueByTypes.add(chargueByType);
        when(chargueByTypeRepository.findAll()).thenReturn(chargueByTypes);

        // When
        ArrayList<ChargueByTypeEntity> allChargueByTypes = chargueByTypeService.getAllChargueByTypes();

        // Then
        assertThat(allChargueByTypes).isEqualTo(chargueByTypes);
    }

    @Test
    public void testGetAllKmChargueByTypes() {
        // Given
        ArrayList<ChargueByTypeEntity> kmChargueByTypes = new ArrayList<>();
        kmChargueByTypes.add(chargueByType);
        when(chargueByTypeRepository.findBySurchargeType("km")).thenReturn(kmChargueByTypes);

        // When
        ArrayList<ChargueByTypeEntity> allKmChargueByTypes = chargueByTypeService.getAllKmChargueByTypes();

        // Then
        assertThat(allKmChargueByTypes).isEqualTo(kmChargueByTypes);
    }

    @Test
    public void testGetAllAntiqChargueByTypes() {
        // Given
        ArrayList<ChargueByTypeEntity> antiqChargueByTypes = new ArrayList<>();
        antiqChargueByTypes.add(chargueByType);
        when(chargueByTypeRepository.findBySurchargeType("antiquity")).thenReturn(antiqChargueByTypes);

        // When
        ArrayList<ChargueByTypeEntity> allAntiqChargueByTypes = chargueByTypeService.getAllAntiqChargueByTypes();

        // Then
        assertThat(allAntiqChargueByTypes).isEqualTo(antiqChargueByTypes);
    }

    @Test
    public void testGetChargueByTypeById() {
        // Given
        when(chargueByTypeRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(chargueByType));

        // When
        ChargueByTypeEntity chargueByTypeById = chargueByTypeService.getChargueByTypeById(1L);

        // Then
        assertThat(chargueByTypeById).isEqualTo(chargueByType);
    }

    @Test
    public void testUpdateChargueByType() throws Exception {
        // Given
        when(chargueByTypeRepository.save(any(ChargueByTypeEntity.class))).thenReturn(chargueByType);

        // When
        ChargueByTypeEntity updatedChargueByType = chargueByTypeService.updateChargueByType(chargueByType);

        // Then
        assertThat(updatedChargueByType).isEqualTo(chargueByType);
    }

    @Test
    public void testUpdateChargueByTypeException() {
        // Given
        when(chargueByTypeRepository.save(any(ChargueByTypeEntity.class))).thenThrow(new RuntimeException("Error updating"));

        // When
        try {
            chargueByTypeService.updateChargueByType(chargueByType);
        } catch (Exception e) {
            // Then
            assertThat(e.getMessage()).isEqualTo("Error updating");
        }
    }

    @Test
    public void testDeleteChargueByType() throws Exception {
        // When
        chargueByTypeService.deleteChargueByType(1L);
    }

    @Test
    public void testDeleteChargueByTypeException() {
        // Given
        doThrow(new RuntimeException("Error deleting")).when(chargueByTypeRepository).deleteById(1L);

        // When
        try {
            chargueByTypeService.deleteChargueByType(1L);
        } catch (Exception e) {
            // Then
            assertThat(e.getMessage()).isEqualTo("Error deleting");
        }
    }
}