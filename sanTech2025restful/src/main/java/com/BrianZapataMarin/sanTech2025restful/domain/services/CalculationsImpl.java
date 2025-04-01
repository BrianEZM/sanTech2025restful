package com.BrianZapataMarin.sanTech2025restful.domain.services;

import com.BrianZapataMarin.sanTech2025restful.application.model.Calculator;
import com.BrianZapataMarin.sanTech2025restful.application.model.Result;
import com.BrianZapataMarin.sanTech2025restful.application.services.Calculations;
import com.BrianZapataMarin.sanTech2025restful.domain.repositories.CalculatorRepository;
import com.BrianZapataMarin.sanTech2025restful.domain.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculationsImpl implements Calculations {

    @Autowired
    private CalculatorRepository calculatorRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public void calculate(Calculator calculator) {
        Result result = new Result();

        result.setResult(switch (calculator.getOperation()) {
            case 1 -> calculator.getNum1() + calculator.getNum2();
            case 2 -> calculator.getNum1() - calculator.getNum2();
            case 3 -> calculator.getNum1() * calculator.getNum2();
            case 4 -> {
                if (calculator.getNum2() == 0) {
                    throw new IllegalArgumentException("No es posible dividir por cero");
                }
                yield calculator.getNum1() / calculator.getNum2();
            }
            default -> throw new IllegalArgumentException("Operación invalida");
        });

        result.setOperation(calculator.getOperation());
        result.setCalculator(calculator);
        calculator.setResult(result.getResult());

        saveOperation(calculator);
        saveResult(result);

    }

    @Override
    public void saveOperation(Calculator calculator) {
        calculatorRepository.save(calculator);
    }

    @Override
    public void saveResult(Result result) {
        resultRepository.save(result);
    }

    @Override
    public List<Calculator> getAllCalculations() {
        return calculatorRepository.findAll();
    }

    @Override
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    @Override
    public void deleteCalculation(Long id) {
        calculatorRepository.deleteById(id);
    }

    @Override
    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }
}