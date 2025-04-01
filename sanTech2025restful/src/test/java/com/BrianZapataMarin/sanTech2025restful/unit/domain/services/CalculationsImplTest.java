package com.BrianZapataMarin.sanTech2025restful.unit.domain.services;

import com.BrianZapataMarin.sanTech2025restful.application.model.Calculator;
import com.BrianZapataMarin.sanTech2025restful.application.model.Result;
import com.BrianZapataMarin.sanTech2025restful.domain.services.CalculationsImpl;
import com.BrianZapataMarin.sanTech2025restful.domain.repositories.CalculatorRepository;
import com.BrianZapataMarin.sanTech2025restful.domain.repositories.ResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculationsImplTest {

    @Mock
    private CalculatorRepository calculatorRepository;

    @Mock
    private ResultRepository resultRepository;

    @InjectMocks
    private CalculationsImpl calculationsService;

    private Calculator calculator;
    private Result result;

    @BeforeEach
    void setUp() {
        calculator = new Calculator(10, 5, 1, 0);
        result = new Result(1, 15, calculator);
    }

    @Test
    void calculate_add_success() {
        calculationsService.calculate(calculator);
        assertEquals(15, calculator.getResult());
        verify(calculatorRepository, times(1)).save(calculator);
        verify(resultRepository, times(1)).save(any(Result.class));
    }

    @Test
    void calculate_subtract_success() {
        calculator.setOperation(2);
        result.setResult(5);

        calculationsService.calculate(calculator);
        assertEquals(5, calculator.getResult());
        verify(calculatorRepository, times(1)).save(calculator);
        verify(resultRepository, times(1)).save(any(Result.class));
    }

    @Test
    void calculate_multiply_success() {
        calculator.setOperation(3);
        result.setResult(50);

        calculationsService.calculate(calculator);
        assertEquals(50, calculator.getResult());
        verify(calculatorRepository, times(1)).save(calculator);
        verify(resultRepository, times(1)).save(any(Result.class));
    }

    @Test
    void calculate_divide_success() {
        calculator.setOperation(4);
        result.setResult(2);

        calculationsService.calculate(calculator);
        assertEquals(2, calculator.getResult());
        verify(calculatorRepository, times(1)).save(calculator);
        verify(resultRepository, times(1)).save(any(Result.class));
    }

    @Test
    void calculate_divide_by_zero_throws_exception() {
        calculator.setNum2(0);
        calculator.setOperation(4);

        assertThrows(IllegalArgumentException.class, () -> calculationsService.calculate(calculator));
        verify(calculatorRepository, never()).save(calculator);
        verify(resultRepository, never()).save(any(Result.class));
    }

    @Test
    void calculate_invalid_operation_throws_exception() {
        calculator.setOperation(5);

        assertThrows(IllegalArgumentException.class, () -> calculationsService.calculate(calculator));
        verify(calculatorRepository, never()).save(calculator);
        verify(resultRepository, never()).save(any(Result.class));
    }

    @Test
    void saveOperation_success() {
        calculationsService.saveOperation(calculator);
        verify(calculatorRepository, times(1)).save(calculator);
    }

    @Test
    void saveResult_success() {
        calculationsService.saveResult(result);
        verify(resultRepository, times(1)).save(result);
    }

    @Test
    void getAllCalculations_success() {
        List<Calculator> calculatorList = Arrays.asList(calculator);
        when(calculatorRepository.findAll()).thenReturn(calculatorList);

        List<Calculator> resultList = calculationsService.getAllCalculations();
        assertEquals(calculatorList, resultList);
        verify(calculatorRepository, times(1)).findAll();
    }

    @Test
    void getAllResults_success() {
        List<Result> resultList = Arrays.asList(result);
        when(resultRepository.findAll()).thenReturn(resultList);

        List<Result> resultListFromService = calculationsService.getAllResults();
        assertEquals(resultList, resultListFromService);
        verify(resultRepository, times(1)).findAll();
    }

    @Test
    void deleteCalculation_success() {
        calculationsService.deleteCalculation(1L);
        verify(calculatorRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteResult_success() {
        calculationsService.deleteResult(1L);
        verify(resultRepository, times(1)).deleteById(1L);
    }
}