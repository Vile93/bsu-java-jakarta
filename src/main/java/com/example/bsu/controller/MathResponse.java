package com.example.bsu.controller;

public class MathResponse   {
    private final double value;

    public  MathResponse(double value) {
        this.value = value;
    }
    public double getTwice() {
        return value*2;
    }
}
