package com.example.prc.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity

@NamedQueries({
        @NamedQuery(
                name = "isDadosBiometricosUsed",
                query = "SELECT 1 FROM UtenteDadosBiometricos udb where udb.tipoDadosBiometricos.id = :id" // JPQL
        )
})

public class UtenteDadosBiometricos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @JoinColumn(name = "tidadosbiometricos_id")
    @ManyToOne
    private TipoDadosBiometricos tipoDadosBiometricos;

    @ManyToOne
    @JoinColumn(name = "EMAIL_UTENTE")
    @NotNull
    private Utente utente;

    @NotNull
    private Date data_observacao;

    @NotNull
    private String valor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted_at;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    public UtenteDadosBiometricos() {
    }

    public UtenteDadosBiometricos(TipoDadosBiometricos tipoDadosBiometricos, Date data_observacao, String valor, Utente utente) {
        this.tipoDadosBiometricos = tipoDadosBiometricos;
        this.data_observacao = data_observacao;
        this.valor = valor;
        this.created_at = new Date();
        this.utente = utente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoDadosBiometricos getTipoDadosBiometricos() {
        return tipoDadosBiometricos;
    }

    public void setTipoDadosBiometricos(TipoDadosBiometricos tipoDadosBiometricos) {
        this.tipoDadosBiometricos = tipoDadosBiometricos;
    }

    public Date getData_observacao() {
        return data_observacao;
    }

    public void setData_observacao(Date data_observacao) {
        this.data_observacao = data_observacao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
