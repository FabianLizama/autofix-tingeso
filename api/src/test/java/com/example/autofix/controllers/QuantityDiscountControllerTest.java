package com.example.autofix.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

import com.example.autofix.entities.QuantityDiscountEntity;
import com.example.autofix.services.QuantityDiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

@WebMvcTest(QuantityDiscountController.class)
public class QuantityDiscountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuantityDiscountService quantityDiscountService;

    private QuantityDiscountEntity mockQuantityDiscount;
    private ArrayList<QuantityDiscountEntity> quantityDiscountList;

    @BeforeEach
    void setUp() {
        mockQuantityDiscount = new QuantityDiscountEntity();
        quantityDiscountList = new ArrayList<>(Arrays.asList(mockQuantityDiscount));
    }

    @Test
    void testGetAllQuantityDiscounts() throws Exception {
        given(quantityDiscountService.getAllQuantityDiscounts()).willReturn(quantityDiscountList);
        mockMvc.perform(get("/api/v1/quantity-discount/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    void testGetQuantityDiscountById() throws Exception {
        given(quantityDiscountService.getQuantityDiscountById(1L)).willReturn(mockQuantityDiscount);
        mockMvc.perform(get("/api/v1/quantity-discount/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testSaveQuantityDiscount() throws Exception {
        given(quantityDiscountService.saveQuantityDiscount(any(QuantityDiscountEntity.class))).willReturn(mockQuantityDiscount);
        mockMvc.perform(post("/api/v1/quantity-discount/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": \"1\", \"amount\": \"10\" }"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateQuantityDiscount() throws Exception {
        
        given(quantityDiscountService.updateQuantityDiscount(any(QuantityDiscountEntity.class))).willReturn(mockQuantityDiscount);
        mockMvc.perform(put("/api/v1/quantity-discount/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": \"1\", \"amount\": \"10\" }"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteQuantityDiscount() throws Exception {
        willDoNothing().given(quantityDiscountService).deleteQuantityDiscount(1L);
        mockMvc.perform(delete("/api/v1/quantity-discount/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdateQuantityDiscountException() throws Exception {
        given(quantityDiscountService.updateQuantityDiscount(any(QuantityDiscountEntity.class))).willThrow(Exception.class);
        mockMvc.perform(put("/api/v1/quantity-discount/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteQuantityDiscountException() throws Exception {
        willThrow(Exception.class).given(quantityDiscountService).deleteQuantityDiscount(1L);
        mockMvc.perform(delete("/api/v1/quantity-discount/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
