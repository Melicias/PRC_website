package com.example.prc.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Prescricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String descricao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date validade;

    @ManyToOne
    @NotNull
    private TipoPrescricao tipoPrescricao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted_at;

    public Prescricao() {

    }

    public Prescricao(String descricao, Date validade, TipoPrescricao tipoPrescricao) {
        this.descricao = descricao;
        this.validade = validade;
        this.tipoPrescricao = tipoPrescricao;
        this.created_at = new Date();
    }

    public Prescricao(String descricao, TipoPrescricao tipoPrescricao) {
        this.descricao = descricao;
        this.tipoPrescricao = tipoPrescricao;
        this.created_at = new Date();
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

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
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
}
