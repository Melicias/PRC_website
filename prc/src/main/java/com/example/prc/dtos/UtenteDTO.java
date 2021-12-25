package com.example.prc.dtos;

import com.example.prc.entities.Prc;
import com.example.prc.entities.Prescricao;
import com.example.prc.entities.ProfissionalSaude;
import com.example.prc.entities.UtenteDadosBiometricos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UtenteDTO {
    private String email;
    private String password;
    private String name;
    private Date deleted_at;
    private int blocked;
    private Date dataNasc;
    private List<ProfissionalSaudeDTO> profissionalSaude;
    private List<UtenteDadosBiometricos> dadosBiometricos;
    private List<PrcDTO> prescricoes;


    public UtenteDTO(String email, String password, String name, Date deleted_at, int blocked, Date dataNasc) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.deleted_at = deleted_at;
        this.blocked = blocked;
        this.dataNasc = dataNasc;
        this.profissionalSaude = new ArrayList<>();
        this.dadosBiometricos = new ArrayList<>();
        this.prescricoes = new ArrayList<>();
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

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public List<ProfissionalSaudeDTO> getProfissionalSaude() {
        return profissionalSaude;
    }

    public void setProfissionalSaude(List<ProfissionalSaudeDTO> profissionalSaude) {
        this.profissionalSaude = profissionalSaude;
    }

    public List<UtenteDadosBiometricos> getDadosBiometricos() {
        return dadosBiometricos;
    }

    public void setDadosBiometricos(List<UtenteDadosBiometricos> dadosBiometricos) {
        this.dadosBiometricos = dadosBiometricos;
    }

    public List<PrcDTO> getPrescricoes() {
        return prescricoes;
    }

    public void setPrescricoes(List<PrcDTO> prescricoes) {
        this.prescricoes = prescricoes;
    }
}
