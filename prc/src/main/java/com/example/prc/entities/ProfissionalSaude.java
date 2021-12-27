package com.example.prc.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllProfissionalSaude",
                query = "SELECT ps FROM ProfissionalSaude ps ORDER BY ps.name" // JPQL
        ),
        @NamedQuery(
                name = "getProfissionalSaude",
                query = "SELECT ps FROM ProfissionalSaude ps WHERE ps.email = :email ORDER BY ps.name" // JPQL
        ),
        @NamedQuery(
                name = "getProfissionaisSemEsteUtente",
                query = "SELECT distinct ps  FROM ProfissionalSaude ps left join fetch ps.utentes u where not exists (select 1 from ps.utentes uu where uu.email = :email) ORDER BY ps.name" // JPQL
        )
})
public class ProfissionalSaude extends User{
    @NotNull
    @JoinColumn(name = "tipoprofissional_id")
    @ManyToOne
    private TipoProfissional tipoProfissional;

    @ManyToMany
    @JoinTable(name = "UTENTE_PROFISSIONALSAUDE",
            joinColumns = @JoinColumn(name = "EMAIL_PROFISSIONALSAUDE", referencedColumnName = "EMAIL"),
            inverseJoinColumns = @JoinColumn(name = "EMAIL_UTENTE", referencedColumnName = "EMAIL"))
    private List<Utente> utentes;

    @OneToMany
    private List<Prc> prcs;

    public ProfissionalSaude() {
        this.utentes = new ArrayList<>();
        this.prcs = new ArrayList<>();
    }

    public ProfissionalSaude(String password, String name, String email, TipoProfissional tipoProfissional) {
        super(password, name, email);
        this.tipoProfissional = tipoProfissional;
        this.utentes = new ArrayList<>();
        this.prcs = new ArrayList<>();
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

    public List<Prc> getPrcs() {
        return prcs;
    }

    public void setPrcs(List<Prc> prcs) {
        this.prcs = prcs;
    }
}
