package com.example.prc.ejbs;

import com.example.prc.entities.ProfissionalSaude;
import com.example.prc.entities.Utente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Stateless
public class UtenteBean {
    @PersistenceContext
    EntityManager em;

    public void create(String password, String name, String email, Date dataNasc, String emailProfissionalSaude){
        Utente utente = em.find(Utente.class, email);
        ProfissionalSaude profissionalSaude = em.find(ProfissionalSaude.class, emailProfissionalSaude);
        if (utente == null && profissionalSaude != null) {
            Utente newUtente = new Utente(password,name,email,dataNasc,profissionalSaude);
            profissionalSaude.addUtente(newUtente);
            em.persist(newUtente);
        }
    }
}
