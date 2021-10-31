package com.example.prc.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
public class ProfissionalSaude extends User{
    @NotNull
    @ManyToOne
    private TipoProfissional tipoProfissional;
    @OneToMany
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

    public void removerrUtente(Utente utente){
        this.utentes.remove(utente);
    }

    public void removerrUtente(int index){
        this.utentes.remove(index);
    }
}
