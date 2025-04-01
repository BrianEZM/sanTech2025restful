package com.BrianZapataMarin.sanTech2025restful.unit.application.controllers;

import com.BrianZapataMarin.sanTech2025restful.application.controllers.CalculatorController;
import com.BrianZapataMarin.sanTech2025restful.application.model.Calculator;
import com.BrianZapataMarin.sanTech2025restful.application.model.Result;
import com.BrianZapataMarin.sanTech2025restful.application.services.Calculations;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private Calculations calculationsService;

    private Calculator calculator;
    private Result result;

    @BeforeEach
    void setUp() {
        calculator = new Calculator(10, 5, 1, 0);
        result = new Result(1, 15, calculator);
    }

    @Test
    void calculate_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/calculadora/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(calculator)))
                .andExpect(status().isCreated());

        verify(calculationsService, times(1)).calculate(any(Calculator.class));
    }

    @Test
    void calculate_failure_invalid_argument() throws Exception {
        doThrow(new IllegalArgumentException("Invalid argument")).when(calculationsService).calculate(any(Calculator.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/calculadora/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(calculator)))
                .andExpect(status().isBadRequest());

        verify(calculationsService, times(1)).calculate(any(Calculator.class));
    }

    @Test
    void getAllCalculations_success() throws Exception {
        when(calculationsService.getAllCalculations()).thenReturn(Arrays.asList(calculator));

        mockMvc.perform(MockMvcRequestBuilders.get("/calculadora/operaciones"))
                .andExpect(status().isOk());

        verify(calculationsService, times(1)).getAllCalculations();
    }

    @Test
    void getAllResults_success() throws Exception {
        when(calculationsService.getAllResults()).thenReturn(Arrays.asList(result));

        mockMvc.perform(MockMvcRequestBuilders.get("/calculadora/resultados"))
                .andExpect(status().isOk());

        verify(calculationsService, times(1)).getAllResults();
    }

    @Test
    void deleteCalculation_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/calculadora/operacion/1"))
                .andExpect(status().isNoContent());

        verify(calculationsService, times(1)).deleteCalculation(1L);
    }

    @Test
    void deleteResult_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/calculadora/resultado/1"))
                .andExpect(status().isNoContent());

        verify(calculationsService, times(1)).deleteResult(1L);
    }
}