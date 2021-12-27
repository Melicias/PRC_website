package com.example.prc.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany
    @NotNull
    private List<Prescricao> prescricoes;

    public TipoPrescricao() {
        this.prescricoes = new ArrayList<>();
    }

    public TipoPrescricao(String name) {
        this.name = name;
        this.prescricoes = new ArrayList<>();
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
