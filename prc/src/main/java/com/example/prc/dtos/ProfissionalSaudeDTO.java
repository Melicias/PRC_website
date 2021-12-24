package com.example.prc.dtos;


import com.example.prc.entities.TipoProfissional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfissionalSaudeDTO {
    private String email;
    private String password;
    private String name;
    private Date deleted_at;
    private int blocked;
    private TipoProfissional tipoProfissional;
    private List<UtenteDTO> utentes;
    private List<PrcDTO> prcs;

    public ProfissionalSaudeDTO() {

    }

    public ProfissionalSaudeDTO(String email, String password, String name, Date deleted_at, int blocked,TipoProfissional tipoProfissional) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.deleted_at = deleted_at;
        this.blocked = blocked;
        this.tipoProfissional = tipoProfissional;
        this.utentes = new ArrayList<>();
        this.prcs = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getBlocked() {
        return blocked;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

    public List<UtenteDTO> getUtentes() {
        return utentes;
    }

    public void setUtentes(List<UtenteDTO> utentes) {
        this.utentes = utentes;
    }

    public TipoProfissional getTipoProfissional() {
        return tipoProfissional;
    }

    public void setTipoProfissional(TipoProfissional tipoProfissional) {
        this.tipoProfissional = tipoProfissional;
    }

    public List<PrcDTO> getPrcs() {
        return prcs;
    }

    public void setPrcs(List<PrcDTO> prcs) {
        this.prcs = prcs;
    }
}
