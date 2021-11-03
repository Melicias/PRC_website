package com.example.prc.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;

@Entity
public class TipoProfissional {
    @Id
    @NotNull
    private String name;

    public TipoProfissional() {

    }

    public TipoProfissional(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
