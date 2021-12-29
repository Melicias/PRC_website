package com.example.prc.ejbs;

import com.example.prc.entities.TipoDadosBiometricos;
import com.example.prc.entities.TipoProfissional;
import com.example.prc.entities.UtenteDadosBiometricos;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Stateless
public class TipoDadosBiometricosBean {
    @PersistenceContext
    EntityManager em;

    public void create(String name, double min, double max, String quantitativo)
            throws MyEntityExistsException, MyConstraintViolationException {
        TipoDadosBiometricos tipoDadosBiometricos = findTipoDadoBiometrico(name);
        if(tipoDadosBiometricos != null) throw new MyEntityExistsException("Name already in use!");
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
        try{
            return (TipoDadosBiometricos) em.createNamedQuery("getTipoDadosBiometricosByName").setParameter("name", name).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    public TipoDadosBiometricos deleteTipoDadoBiometrico(int id)
            throws MyEntityNotFoundException {
        TipoDadosBiometricos tdb = em.find(TipoDadosBiometricos.class,id);
        if(tdb == null)
            throw new MyEntityNotFoundException("Type of Biometric data not found");

        List<UtenteDadosBiometricos> utb = (List<UtenteDadosBiometricos>) em.createNamedQuery("isDadosBiometricosUsed").setParameter("id",id).setMaxResults(1).getResultList();
        if(utb.isEmpty()){
            em.remove(tdb);
            em.flush();
            return null;
        }
        if(tdb.getDeleted_at() == null){
            tdb.setDeleted_at(new Date());
        }else{
            tdb.setDeleted_at(null);
        }
        em.persist(tdb);
        em.flush();
        return tdb;
    }
}
