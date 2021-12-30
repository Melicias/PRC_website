package com.example.prc.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;

@Entity
public class TipoDadosBiometricosQuantitativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double min;
    private double max;
    @NotNull
    private String name;
    @NotNull
    @JoinColumn(name = "tipodadosbiometricos_id")
    @ManyToOne
    private TipoDadosBiometricos tipoDadosBiometricos;

    public TipoDadosBiometricosQuantitativo(double min, double max, String name, TipoDadosBiometricos tipoDadosBiometricos) {
        this.min = min;
        this.max = max;
        this.name = name;
        this.tipoDadosBiometricos = tipoDadosBiometricos;
    }

    public TipoDadosBiometricosQuantitativo(double min, double max, String name) {
        this.min = min;
        this.max = max;
        this.name = name;
    }

    public TipoDadosBiometricosQuantitativo() {
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

    public TipoDadosBiometricos getTipoDadosBiometricos() {
        return tipoDadosBiometricos;
    }

    public void setTipoDadosBiometricos(TipoDadosBiometricos tipoDadosBiometricos) {
        this.tipoDadosBiometricos = tipoDadosBiometricos;
    }
}
