package com.example.prc.dtos;

import java.io.Serializable;
import java.util.Date;

public class TipoProfissionalDTO implements Serializable {
    private int id;
    private String name;
    private Date deleted_at;

    public TipoProfissionalDTO() {
    }

    public TipoProfissionalDTO(int id, String name, Date deleted_at) {
        this.id = id;
        this.name = name;
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

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }
}
