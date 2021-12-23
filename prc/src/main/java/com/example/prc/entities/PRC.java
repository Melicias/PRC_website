package com.example.prc.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class PRC {
    @NotNull
    @ManyToOne
    private Utente utente;

    @NotNull
    @ManyToOne
    private ProfissionalSaude profissionalSaude;

    @NotNull
    private String doenca;

    @Temporal(TemporalType.TIMESTAMP)
    private Date validade;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted_at;

    @OneToMany
    private List<Prescricao> prescricoes;

    public PRC(Utente utente, ProfissionalSaude profissionalSaude, String doenca, Date validade) {
        this.utente = utente;
        this.profissionalSaude = profissionalSaude;
        this.doenca = doenca;
        this.validade = validade;
        this.created_at = new Date();
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public ProfissionalSaude getProfissionalSaude() {
        return profissionalSaude;
    }

    public void setProfissionalSaude(ProfissionalSaude profissionalSaude) {
        this.profissionalSaude = profissionalSaude;
    }

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }
}
