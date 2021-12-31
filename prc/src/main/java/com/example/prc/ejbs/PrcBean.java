package com.example.prc.ejbs;

import com.example.prc.entities.*;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;

@Stateless
public class PrcBean {
    @PersistenceContext
    EntityManager em;

    public void create(String emailUtente, String emailProfissionalSaude, String doenca, Date validade, int idPrescricao)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        Utente utente = em.find(Utente.class, emailUtente);
        if (utente == null) {
            throw new MyEntityNotFoundException("User not found");
        }
        ProfissionalSaude profissionalSaude = em.find(ProfissionalSaude.class, emailProfissionalSaude);
        if(profissionalSaude == null)
            throw new MyEntityNotFoundException("Healthcare specialist not found.");
        Date date = new Date();
        if (validade.before(date)) {
            throw new MyEntityNotFoundException("Validation date must be after current date.");
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

    public void update(int idPrc, String doenca, Date validade)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        Prc prc = em.find(Prc.class, idPrc);
        if (prc == null) {
            throw new MyEntityNotFoundException("PRC not found.");
        }
        Date date = new Date();
        if (validade.before(date)) {
            throw new MyEntityNotFoundException("Validation date must be after current date.");
        }
        try {
            if (doenca != null)
                prc.setDoenca(doenca);
            prc.setValidade(validade);
            em.persist(prc);
            em.flush();
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Prc delete(int idPrc)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        Prc prc = em.find(Prc.class, idPrc);
        if(prc == null)
            throw new MyEntityNotFoundException("Prc not found");
        Date date = new Date();
        if (prc.getValidade().before(date)) {
            throw new MyEntityNotFoundException("This PRC is valid so cant be deleted.");
        }

        if(prc.getDeleted_at() == null){
            prc.setDeleted_at(new Date());
        }else{
            prc.setDeleted_at(null);
        }

        em.persist(prc);
        em.flush();
        return prc;
    }

    public Prescricao addPrescricao(int idPrc, int idPrescricao)
            throws MyEntityNotFoundException, MyConstraintViolationException, MyEntityExistsException {
        Prc prc = em.find(Prc.class, idPrc);
        if(prc == null)
            throw new MyEntityNotFoundException("The Prc was not found..");
        Prescricao prescricao = em.find(Prescricao.class, idPrescricao);
        if(prescricao == null)
            throw new MyEntityNotFoundException("The Prescription was not found..");
        List<Prc> prcs = prescricao.getPrcs();
        for(int i = 0; i <prcs.size(); i++){
            if(prcs.get(i).getId() == idPrc){
                throw new MyEntityExistsException("This prescription already exists in this PRC");
            }
        }
        try {
            prc.addPrescricao(prescricao);
            prescricao.addPrc(prc);
            em.merge(prc);
            em.merge(prescricao);
            return prescricao;
        } catch (
                ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Prescricao removePrescricao(int idPrc, int idPrescricao)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        Prc prc = em.find(Prc.class, idPrc);
        if(prc == null)
            throw new MyEntityNotFoundException("The Prc doesnt exists...");
        Prescricao prescricao = em.find(Prescricao.class, idPrescricao);
        if(prescricao == null)
            throw new MyEntityNotFoundException("The Prescription does not exists..");
        try {
            prc.removePrescricao(prescricao);
            prescricao.removePrc(prc);
            em.merge(prc);
            em.merge(prescricao);
            return prescricao;
        } catch (
                ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

}
