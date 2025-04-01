package com.BrianZapataMarin.sanTech2025restful.application.controllers;

import com.BrianZapataMarin.sanTech2025restful.application.model.Calculator;
import com.BrianZapataMarin.sanTech2025restful.application.model.Result;
import com.BrianZapataMarin.sanTech2025restful.application.services.Calculations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/calculadora")
public class CalculatorController {

    @Autowired
    private Calculations calculationsService;

    @PostMapping("/calcular")
    public ResponseEntity<Void> calculate(@RequestBody Calculator calculator) {
        try {
            calculationsService.calculate(calculator);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/operaciones")
    public ResponseEntity<List<Calculator>> getAllCalculations() {
        return new ResponseEntity<>(calculationsService.getAllCalculations(), HttpStatus.OK);
    }

    @GetMapping("/resultados")
    public ResponseEntity<List<Result>> getAllResults() {
        return new ResponseEntity<>(calculationsService.getAllResults(), HttpStatus.OK);
    }

    @DeleteMapping("/operacion/{id}")
    public ResponseEntity<Void> deleteCalculation(@PathVariable Long id) {
        calculationsService.deleteCalculation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/resultado/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        calculationsService.deleteResult(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}