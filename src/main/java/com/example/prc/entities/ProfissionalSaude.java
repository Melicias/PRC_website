package com.example.prc.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class ProfissionalSaude extends User{
    @NotNull
    @JoinColumn(name = "tipoprofissional_id")
    @ManyToOne
    private TipoProfissional tipoProfissional;

    @ManyToMany
    @JoinTable(name = "UTENTES_PROFISSIONAL_SAUDE",
            joinColumns = @JoinColumn(name = "EMAIL_UTENTE", referencedColumnName = "EMAIL"),
            inverseJoinColumns = @JoinColumn(name = "EMAIL_PROFISSIONAL_SAUDE", referencedColumnName = "EMAIL"))
    private List<Utente> utentes;

    public ProfissionalSaude() {
        this.utentes = new ArrayList<>();
    }

    public ProfissionalSaude(String password, String name, String email, TipoProfissional tipoProfissional) {
        super(password, name, email);
        this.tipoProfissional = tipoProfissional;
        this.utentes = new ArrayList<>();
    }

    public TipoProfissional getTipoProfissional() {
        return tipoProfissional;
    }

    public void setTipoProfissional(TipoProfissional tipoProfissional) {
        this.tipoProfissional = tipoProfissional;
    }

    public List<Utente> getUtentes() {
        return utentes;
    }

    public void setUtentes(List<Utente> utentes) {
        this.utentes = utentes;
    }

    public void adicionarUtente(Utente utente){
        this.utentes.add(utente);
    }

    public void removerUtente(Utente utente){
        this.utentes.remove(utente);
    }

    public void removerUtente(int index){
        this.utentes.remove(index);
    }

    public void addUtente(Utente utente) {
        utentes.add(utente);
    }
}
