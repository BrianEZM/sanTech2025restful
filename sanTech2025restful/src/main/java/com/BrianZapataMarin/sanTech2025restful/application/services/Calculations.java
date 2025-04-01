package com.BrianZapataMarin.sanTech2025restful.application.services;

import com.BrianZapataMarin.sanTech2025restful.application.model.Calculator;
import com.BrianZapataMarin.sanTech2025restful.application.model.Result;

import java.util.List;

public interface Calculations {

    void calculate(Calculator calculator);

    void saveOperation(Calculator calculator);

    void saveResult(Result result);

    List<Calculator> getAllCalculations();

    List<Result> getAllResults();

    void deleteCalculation(Long id);

    void deleteResult(Long id);
}
