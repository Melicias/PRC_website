package com.example.prc.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllTiposPrescricao",
                query = "SELECT tp FROM TipoPrescricao tp ORDER BY tp.name" // JPQL
        )
})
public class TipoPrescricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(unique=true)
    private String name;

    public TipoPrescricao() {

    }

    public TipoPrescricao(String name) {
        this.name = name;
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
}
