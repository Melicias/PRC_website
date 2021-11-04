package com.example.prc.ejbs;

import com.example.prc.entities.ProfissionalSaude;
import com.example.prc.entities.Utente;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.Date;

@Stateless
public class UtenteBean {
    @PersistenceContext
    EntityManager em;

    public void create(String password, String name, String email, Date dataNasc, String emailProfissionalSaude)
            throws MyEntityExistsException, MyConstraintViolationException {
        Utente utente = em.find(Utente.class, email);
        if(utente == null)
            throw new MyEntityExistsException();
        ProfissionalSaude profissionalSaude = em.find(ProfissionalSaude.class, emailProfissionalSaude);
        if(profissionalSaude == null)
            throw new MyEntityExistsException();
        try {
            Utente newUtente = new Utente(password, name, email, dataNasc, profissionalSaude);
            profissionalSaude.addUtente(newUtente);
            em.persist(newUtente);
        }catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }
}
