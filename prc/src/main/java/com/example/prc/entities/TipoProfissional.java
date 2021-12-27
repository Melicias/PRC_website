package com.example.prc.entities;

import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity

@NamedQueries({
        @NamedQuery(
                name = "getAllTipoProfisiional",
                query = "SELECT tp FROM TipoProfissional tp ORDER BY tp.id" // JPQL
        ),
        @NamedQuery(
                name = "getTipoProfissionalByName",
                query = "SELECT tp FROM TipoProfissional tp where tp.name = :name" // JPQL
        ),
        @NamedQuery(
                name = "isTipoProfissionalUsed",
                query = "SELECT 1 FROM ProfissionalSaude ps where ps.tipoProfissional.id = :id" // JPQL
        )
})


public class TipoProfissional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(unique=true)
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted_at;
    @OneToMany(mappedBy = "tipoProfissional")
    private List<ProfissionalSaude> profissionaisSaude;

    public TipoProfissional() {
        this.profissionaisSaude = new ArrayList<>();
    }

    public TipoProfissional(String name) {
        this.name = name;
        this.profissionaisSaude = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<ProfissionalSaude> getProfissionaisSaude() {
        return profissionaisSaude;
    }

    public void setProfissionaisSaude(List<ProfissionalSaude> profissionaisSaude) {
        this.profissionaisSaude = profissionaisSaude;
    }

    public void addProfissionaisSaude(ProfissionalSaude ps){
        profissionaisSaude.add(ps);
    }

    public void removeProfissionaisSaude(ProfissionalSaude ps){
        profissionaisSaude.remove(ps);
    }
}
