package com.example.prc.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllPrescricoes",
                query = "SELECT tp FROM Prescricao tp ORDER BY tp.name" // JPQL
        ),
        @NamedQuery(
                name = "getPrescricaoByName",
                query = "SELECT tp FROM Prescricao tp where tp.name = :name" // JPQL
        )
})

public class Prescricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String descricao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "tipoprescricao_id")
    @NotNull
    private TipoPrescricao tipoPrescricao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted_at;

    @ManyToMany
    @JoinTable(name = "PRESCRICAO_PRC",
            joinColumns = @JoinColumn(name = "ID_PRESCRICAO", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRC", referencedColumnName = "ID"))
    private List<Prc> prcs;

    public Prescricao() {
        this.prcs = new ArrayList<>();
    }

    public Prescricao(String descricao, String name, TipoPrescricao tipoPrescricao) {
        this.descricao = descricao;
        this.name = name;
        this.tipoPrescricao = tipoPrescricao;
        this.created_at = new Date();
        this.prcs = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public TipoPrescricao getTipoPrescricao() {
        return tipoPrescricao;
    }

    public void setTipoPrescricao(TipoPrescricao tipoPrescricao) {
        this.tipoPrescricao = tipoPrescricao;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPrc(Prc prc) {
        prcs.add(prc);
    }

    public List<Prc> getPrcs() {
        return prcs;
    }

    public void setPrcs(List<Prc> prcs) {
        this.prcs = prcs;
    }
}
