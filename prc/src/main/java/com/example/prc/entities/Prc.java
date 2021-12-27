package com.example.prc.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllPrcs",
                query = "SELECT p FROM Prc p ORDER BY p.created_at" // JPQL
        ),
        @NamedQuery(
                name = "getAllPrcsByUtente",
                query = "SELECT p FROM Prc p where p.utente = :utente ORDER BY p.created_at" // JPQL
        )
})
public class Prc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "EMAIL_UTENTE")
    @NotNull
    private Utente utente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "EMAIL_PROFISSIONALSAUDE")
    private ProfissionalSaude profissionalSaude;

    @NotNull
    private String doenca;

    @Temporal(TemporalType.TIMESTAMP)
    private Date validade;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted_at;

    @ManyToMany(mappedBy = "prcs")
    private List<Prescricao> prescricoes;

    public Prc() {
        this.prescricoes = new ArrayList<>();
    }

    public Prc(Utente utente, ProfissionalSaude profissionalSaude, String doenca, Date validade) {
        this.utente = utente;
        this.profissionalSaude = profissionalSaude;
        this.doenca = doenca;
        this.validade = validade;
        this.created_at = new Date();
        this.prescricoes = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void addPrescricao(Prescricao prescricao) {
        prescricoes.add(prescricao);
    }

    public List<Prescricao> getPrescricoes() {
        return prescricoes;
    }

    public void setPrescricoes(List<Prescricao> prescricoes) {
        this.prescricoes = prescricoes;
    }

}
