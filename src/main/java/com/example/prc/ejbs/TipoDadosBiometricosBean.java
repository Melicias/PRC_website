package com.example.prc.ejbs;

import com.example.prc.entities.Admin;
import com.example.prc.entities.TipoDadosBiometricos;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

@Stateless
public class TipoDadosBiometricosBean {
    @PersistenceContext
    EntityManager em;

    public void create(String name, int min, int max, String quantitativo)
            throws MyEntityExistsException, MyConstraintViolationException {
        /*TipoDadosBiometricos tipoDadoBiometrico = em.find(TipoDadosBiometricos.class, name);
        if(tipoDadoBiometrico != null)
            throw new MyEntityExistsException("Name already in use: " + name);
        */
        TipoDadosBiometricos tipoDadoBiometrico;
        try {
            if(quantitativo == null) {
                tipoDadoBiometrico = new TipoDadosBiometricos(name, min, max);
            }else{
                tipoDadoBiometrico = new TipoDadosBiometricos(name, quantitativo);
            }
            em.persist(tipoDadoBiometrico);
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }
}
