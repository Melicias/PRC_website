package com.example.prc.ejbs;

import com.example.prc.entities.TipoDadosBiometricos;
import com.example.prc.entities.TipoProfissional;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;

@Stateless
public class TipoDadosBiometricosBean {
    @PersistenceContext
    EntityManager em;

    public void create(String name, double min, double max, String quantitativo)
            throws MyEntityExistsException, MyConstraintViolationException {
        try {
            TipoDadosBiometricos tipoDadoBiometrico;
            if(quantitativo == null) {
                tipoDadoBiometrico = new TipoDadosBiometricos(name, min, max);
            }else{
                tipoDadoBiometrico = new TipoDadosBiometricos(name, quantitativo);
            }
            em.persist(tipoDadoBiometrico);
            em.flush();
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public void update(int id, double min, double max, String quantitativo)
            throws MyEntityExistsException, MyConstraintViolationException {
        try {
            TipoDadosBiometricos tdb = em.find(TipoDadosBiometricos.class,id);
            if(tdb.getType() == 2) {
                tdb.setMin(min);
                tdb.setMax(max);
            }else{
                tdb.setQuantitativo(quantitativo);
            }
            em.persist(tdb);
            em.flush();
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public List<TipoDadosBiometricos> getAllTipoDadosBiometricos() {
        return (List<TipoDadosBiometricos>) em.createNamedQuery("getAllTipoDadosBiometricos").getResultList();
    }

    public TipoDadosBiometricos findTipoDadoBiometrico(String name) {
        return (TipoDadosBiometricos) em.createNamedQuery("getTipoDadosBiometricosByName").setParameter("name", name).getSingleResult();
    }

    public TipoDadosBiometricos deleteTipoDadoBiometrico(int id){
        TipoDadosBiometricos tdb = em.find(TipoDadosBiometricos.class,id);
        if(tdb != null){
            if(tdb.getDeleted_at() == null){
                tdb.setDeleted_at(new Date());
            }else{
                tdb.setDeleted_at(null);
            }
            em.persist(tdb);
            em.flush();
        }
        return tdb;
    }
}
