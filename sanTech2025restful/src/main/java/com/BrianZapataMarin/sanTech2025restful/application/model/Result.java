package com.BrianZapataMarin.sanTech2025restful.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int operation;
    private double result;

    @OneToOne
    @JoinColumn(name = "calculator_id")
    private Calculator calculator;

    public Result() {
    }

    public Result(int operation, double result, Calculator calculator) {
        this.operation = operation;
        this.result = result;
        this.calculator = calculator;
    }

    public Long getId() {
        return id;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public Calculator getCalculator() {
        return calculator;
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

}