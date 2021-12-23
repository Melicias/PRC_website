package com.example.prc.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity

@NamedQueries({
        @NamedQuery(
                name = "getAllTipoDadosBiometricos",
                query = "SELECT tdb FROM TipoDadosBiometricos tdb ORDER BY tdb.name" // JPQL
        ),
        @NamedQuery(
                name = "getTipoDadosBiometricosByName",
                query = "SELECT tdb FROM TipoDadosBiometricos tdb where tdb.name = :name" // JPQL
        )
})

public class TipoDadosBiometricos {
    //vai preencher a string
    public static int QUANTITATIVO = 1;
    //vai preencher o min max
    public static int QUALITATIVO = 2;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(unique=true)
    private String name;
    @NotNull
    private int type;
    private double min;
    private double max;
    private String quantitativo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted_at;

    public TipoDadosBiometricos() {

    }

    public TipoDadosBiometricos(String name, double min, double max) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.type = QUALITATIVO;
    }

    public TipoDadosBiometricos(String name, String quantitativo) {
        this.name = name;
        this.quantitativo = quantitativo;
        this.type = QUANTITATIVO;
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
