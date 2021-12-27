package com.example.prc.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrcDTO {
    private int id;
    private UtenteDTO utente;
    private ProfissionalSaudeDTO profissionalSaude;
    private String emailUtente;
    private String emailProfissionalSaude;
    private String doenca;
    private Date validade;
    private Date created_at;
    private Date deleted_at;
    private List<PrescricaoDTO> prescricoes;
    private int idPrescricao;

    public PrcDTO() {
    }

    public PrcDTO(int id, String doenca, Date validade, Date created_at, Date deleted_at) {
        this.id = id;
        this.doenca = doenca;
        this.validade = validade;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
        this.prescricoes = new ArrayList<>();
    }

    public PrcDTO(int id, UtenteDTO utente, ProfissionalSaudeDTO profissionalSaude, String doenca, Date validade, Date created_at, Date deleted_at) {
        this.id = id;
        this.utente = utente;
        this.profissionalSaude = profissionalSaude;
        this.doenca = doenca;
        this.validade = validade;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
        this.prescricoes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UtenteDTO getUtente() {
        return utente;
    }

    public void setUtente(UtenteDTO utente) {
        this.utente = utente;
    }

    public ProfissionalSaudeDTO getProfissionalSaude() {
        return profissionalSaude;
    }

    public void setProfissionalSaude(ProfissionalSaudeDTO profissionalSaude) {
        this.profissionalSaude = profissionalSaude;
    }

    public String getEmailUtente() {
        return emailUtente;
    }

    public void setEmailUtente(String emailUtente) {
        this.emailUtente = emailUtente;
    }

    public String getEmailProfissionalSaude() {
        return emailProfissionalSaude;
    }

    public void setEmailProfissionalSaude(String emailProfissionalSaude) {
        this.emailProfissionalSaude = emailProfissionalSaude;
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

    public List<PrescricaoDTO> getPrescricoes() {
        return prescricoes;
    }

    public void setPrescricoes(List<PrescricaoDTO> prescricoes) {
        this.prescricoes = prescricoes;
    }

    public int getIdPrescricao() {
        return idPrescricao;
    }

    public void setIdPrescricao(int idPrescricao) {
        this.idPrescricao = idPrescricao;
    }
}
