package com.example.prc.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Utente extends User{
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNasc;
    @ManyToOne
    private ProfissionalSaude profissionalSaude;
    @OneToMany
    private List<UtenteDadosBiometricos> dadosBiometricos;
    @OneToMany
    private List<Prescricao> prescricoes;

    public Utente() {
        super();
        this.dadosBiometricos = new ArrayList<>();
        this.prescricoes = new ArrayList<>();
    }

    public Utente(String password, String name, String email, Date dataNasc, ProfissionalSaude profissionalSaude) {
        super(password, name, email);
        this.dataNasc = dataNasc;
        this.profissionalSaude = profissionalSaude;
        this.dadosBiometricos = new ArrayList<>();
        this.prescricoes = new ArrayList<>();
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public List<UtenteDadosBiometricos> getDadosBiometricos() {
        return dadosBiometricos;
    }

    public void setDadosBiometricos(List<UtenteDadosBiometricos> dadosBiometricos) {
        this.dadosBiometricos = dadosBiometricos;
    }

    public List<Prescricao> getPrescricoes() {
        return prescricoes;
    }

    public void setPrescricoes(List<Prescricao> prescricoes) {
        this.prescricoes = prescricoes;
    }

    public void addDadosBiometricos(UtenteDadosBiometricos dados){
        this.dadosBiometricos.add(dados);
    }

    public void addPrecricoes(Prescricao dados){
        this.prescricoes.add(dados);
    }

    public void removerDadosBiometricos(int index){
        this.dadosBiometricos.remove(index);
    }

    public void removerDadosBiometricos(UtenteDadosBiometricos dados){
        this.dadosBiometricos.remove(dados);
    }

    public void removerPrescricao(int index){
        this.prescricoes.remove(index);
    }

    public void removerPrescricao(Prescricao dados){
        this.dadosBiometricos.remove(dados);
    }
}
