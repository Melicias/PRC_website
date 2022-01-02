package com.example.prc.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getUtenteSemProfissional",
                query = "SELECT distinct u FROM Utente u left join fetch u.profissionalSaude ps where not exists (select 1 from u.profissionalSaude p where p.email = :email) and u.deleted_at is null" // JPQL
        ),
        @NamedQuery(
                name = "getUtenteComProfissional",
                query = "SELECT distinct u FROM Utente u left join fetch u.profissionalSaude ps where exists (select 1 from u.profissionalSaude p where p.email = :email) and u.deleted_at is null" // JPQL
        ),
        @NamedQuery(      
                name = "getAllUtentes",
                query = "select  ut FROM Utente ut order by  ut.email"
        ),
        @NamedQuery(
                name = "getUtente",
                query = "SELECT ut FROM Utente ut where ut.email= :email" // JPQL
        )
})
public class Utente extends User{
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNasc;
    @ManyToMany(mappedBy = "utentes")
    private List<ProfissionalSaude> profissionalSaude;
    @OneToMany
    private List<UtenteDadosBiometricos> dadosBiometricos;
    @OneToMany(mappedBy = "utente")
    private List<Prc> prcs;

    public Utente() {
        super();
        this.profissionalSaude = new ArrayList<>();
        this.dadosBiometricos = new ArrayList<>();
        this.prcs = new ArrayList<>();
    }

    public Utente(String password, String name, String email, Date dataNasc) {
        super(password, name, email);
        this.dataNasc = dataNasc;
        this.profissionalSaude = new ArrayList<>();
        this.dadosBiometricos = new ArrayList<>();
        this.prcs = new ArrayList<>();
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

    public List<Prc> getPrcs() {
        return prcs;
    }

    public void setPrcs(List<Prc> prcs) {
        this.prcs = prcs;
    }

    public void addDadosBiometricos(UtenteDadosBiometricos dados){
        this.dadosBiometricos.add(dados);
    }

    public void addPrc(Prc prc){
        this.prcs.add(prc);
    }

    public void addProfissionalSaude(ProfissionalSaude profissionalSaude){
        this.profissionalSaude.add(profissionalSaude);
    }

    public void removeProfissionalSaude(ProfissionalSaude profissionalSaude){
        this.profissionalSaude.remove(profissionalSaude);
    }

    public void removerDadosBiometricos(int index){
        this.dadosBiometricos.remove(index);
    }

    public void removerDadosBiometricos(UtenteDadosBiometricos dados){
        this.dadosBiometricos.remove(dados);
    }

    public void removerPrc(int index){
        this.prcs.remove(index);
    }

    public void removerPrc(Prc prc){
        this.prcs.remove(prc);
    }

    public List<ProfissionalSaude> getProfissionalSaude() {
        return profissionalSaude;
    }

    public void setProfissionalSaude(List<ProfissionalSaude> profissionalSaude) {
        this.profissionalSaude = profissionalSaude;
    }
}
