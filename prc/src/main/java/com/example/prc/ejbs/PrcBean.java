package com.example.prc.ejbs;

import com.example.prc.entities.*;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;

@Stateless
public class PrcBean {
    @PersistenceContext
    EntityManager em;

    public void create(String emailUtente, String emailProfissionalSaude, String doenca, Date validade, int idPrescricao, boolean itsBean)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        Utente utente = em.find(Utente.class, emailUtente);
        if (utente == null) {
            throw new MyEntityNotFoundException("User not found");
        }
        ProfissionalSaude profissionalSaude = em.find(ProfissionalSaude.class, emailProfissionalSaude);
        if(profissionalSaude == null)
            throw new MyEntityNotFoundException("Healthcare specialist not found.");
        if(!itsBean) {
            Date date = new Date();
            if (validade.before(date)) {
                throw new MyEntityNotFoundException("Validation date must be after current date.");
            }
        }
        Prescricao prescricao = em.find(Prescricao.class, idPrescricao);
        if (prescricao == null) {
            throw new MyEntityNotFoundException("Prescription does not exists.");
        }
        try {
            Prc prc = new Prc(utente, profissionalSaude, doenca, validade);
            prc.addPrescricao(prescricao);
            prescricao.addPrc(prc);
            utente.addPrc(prc);
            profissionalSaude.addPrc(prc);
            em.persist(prc);
            em.merge(prescricao);
            em.merge(utente);
            em.merge(profissionalSaude);
            em.flush();
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public List<Prc> getAllPrcs() {
        return (List<Prc>) em.createNamedQuery("getAllPrcs").getResultList();
    }

    public List<Prc> getAllPrcsByUtente(String emailUtente) {
        Utente utente = em.find(Utente.class, emailUtente);
        return (List<Prc>) em.createNamedQuery("getAllPrcsByUtente").setParameter("utente", utente).getResultList();
    }
}
