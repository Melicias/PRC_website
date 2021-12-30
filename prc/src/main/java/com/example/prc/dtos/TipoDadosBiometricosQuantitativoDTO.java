package com.example.prc.dtos;

public class TipoDadosBiometricosQuantitativoDTO {
    private int id;
    private double min;
    private double max;
    private String name;

    public TipoDadosBiometricosQuantitativoDTO(int id, double min, double max, String name) {
        this.id = id;
        this.min = min;
        this.max = max;
        this.name = name;
    }

    public TipoDadosBiometricosQuantitativoDTO(double min, double max, String name) {
        this.min = min;
        this.max = max;
        this.name = name;
    }

    public TipoDadosBiometricosQuantitativoDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
