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

import com.example.autofix.entities.QuantityDiscountEntity;
import com.example.autofix.repositories.QuantityDiscountRepository;

public class QuantityDiscountServiceTest {
    
    @Mock
    private QuantityDiscountRepository quantityDiscountRepository;
    
    @InjectMocks
    private QuantityDiscountService quantityDiscountService;

    private QuantityDiscountEntity quantityDiscount;
    
    @BeforeEach
    public void setup() {
        // This will initialize the mocks and inject them properly
        MockitoAnnotations.openMocks(this);
        
        quantityDiscount = new QuantityDiscountEntity();
        quantityDiscount.setId(1L);
        quantityDiscount.setDieselPercent(0);
        quantityDiscount.setGasPercent(0);
        quantityDiscount.setElectricPercent(0);
        quantityDiscount.setHybridPercent(0);
        quantityDiscount.setNumRepairsStart(0);
        quantityDiscount.setNumRepairsEnd(2);

        when(quantityDiscountRepository.save(any(QuantityDiscountEntity.class))).thenReturn(quantityDiscount);
    }

    @Test
    public void testSaveQuantityDiscount() {
        // When
        QuantityDiscountEntity savedQuantityDiscount = quantityDiscountService.saveQuantityDiscount(quantityDiscount);
        // Then
        assertThat(savedQuantityDiscount).isEqualTo(quantityDiscount);
    }

    @Test
    public void testGetAllQuantityDiscounts() {
        // Given
        ArrayList<QuantityDiscountEntity> quantityDiscounts = new ArrayList<>();
        quantityDiscounts.add(quantityDiscount);

        QuantityDiscountEntity quantityDiscount2 = new QuantityDiscountEntity(
            2L, 3, 5, 0.1, 0.2, 0.3, 0.4
        );

        quantityDiscounts.add(quantityDiscount2);

        when(quantityDiscountRepository.findAll()).thenReturn(quantityDiscounts);

        // When
        ArrayList<QuantityDiscountEntity> foundQuantityDiscounts = quantityDiscountService.getAllQuantityDiscounts();

        // Then
        assertThat(foundQuantityDiscounts).isEqualTo(quantityDiscounts);
    }

    @Test
    public void testGetQuantityDiscountById() {
        // Given
        when(quantityDiscountRepository.findById(1L)).thenReturn(java.util.Optional.of(quantityDiscount));

        // When
        QuantityDiscountEntity foundQuantityDiscount = quantityDiscountService.getQuantityDiscountById(1L);

        // Then
        assertThat(foundQuantityDiscount).isEqualTo(quantityDiscount);
    }

    @Test
    public void testUpdateQuantityDiscount() throws Exception{
        // When
        QuantityDiscountEntity updatedQuantityDiscount = quantityDiscountService.updateQuantityDiscount(quantityDiscount);
        // Then
        assertThat(updatedQuantityDiscount).isEqualTo(quantityDiscount);
    }

    @Test
    public void testUpdateQuantityDiscountException() {
        // Given
        when(quantityDiscountRepository.save(any(QuantityDiscountEntity.class))).thenThrow(new RuntimeException());

        // When
        try {
            quantityDiscountService.updateQuantityDiscount(quantityDiscount);
        } catch (Exception e) {
            // Then
            assertThat(e).isInstanceOf(Exception.class);
        }
    }

    @Test
    public void testDeleteQuantityDiscount() throws Exception {
        // When
        quantityDiscountService.deleteQuantityDiscount(1L);
    }

    @Test
    public void testDeleteQuantityDiscountException() {
        // Given
        doThrow(new RuntimeException()).when(quantityDiscountRepository).deleteById(1L);

        // When
        try {
            quantityDiscountService.deleteQuantityDiscount(1L);
        } catch (Exception e) {
            // Then
            assertThat(e).isInstanceOf(Exception.class);
        }
    }

}
