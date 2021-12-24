package com.example.prc.dtos;

import com.example.prc.entities.TipoPrescricao;
import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.Date;

public class PrescricaoDTO {
    private int id;
    private String name;
    private String descricao;
    private Date created_at;
    private TipoPrescricaoDTO tipoPrescricao;
    private Date deleted_at;

    public PrescricaoDTO() {
    }

    public PrescricaoDTO(int id, String name, String descricao, Date created_at, TipoPrescricaoDTO tipoPrescricao, Date deleted_at) {
        this.id = id;
        this.name = name;
        this.descricao = descricao;
        this.created_at = created_at;
        this.tipoPrescricao = tipoPrescricao;
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

    public TipoPrescricaoDTO getTipoPrescricao() {
        return tipoPrescricao;
    }

    public void setTipoPrescricao(TipoPrescricaoDTO tipoPrescricao) {
        this.tipoPrescricao = tipoPrescricao;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }
}
