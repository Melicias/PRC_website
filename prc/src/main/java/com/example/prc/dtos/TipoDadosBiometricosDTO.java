package com.example.prc.dtos;

import java.util.Date;

public class TipoDadosBiometricosDTO {
    //vai preencher a string
    public static int QUANTITATIVO = 1;
    //vai preencher o min max
    public static int QUALITATIVO = 2;
    private int id;
    private String name;
    private int type;
    private double min;
    private double max;
    private String quantitativo;
    private Date deleted_at;

    public TipoDadosBiometricosDTO(String name, double min, double max,int type, String quantitativo) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.type = type;
        this.quantitativo = quantitativo;
    }

    public TipoDadosBiometricosDTO() {

    }

    public TipoDadosBiometricosDTO(int id, String name, double min, double max, Date deleted_at) {
        this.id = id;
        this.name = name;
        this.min = min;
        this.max = max;
        this.type = QUALITATIVO;
        this.deleted_at = deleted_at;
    }

    public TipoDadosBiometricosDTO(int id, String name, String quantitativo, Date deleted_at) {
        this.id = id;
        this.name = name;
        this.quantitativo = quantitativo;
        this.type = QUANTITATIVO;
        this.deleted_at = deleted_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getQuantitativo() {
        return quantitativo;
    }

    public void setQuantitativo(String quantitativo) {
        this.quantitativo = quantitativo;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }
}
