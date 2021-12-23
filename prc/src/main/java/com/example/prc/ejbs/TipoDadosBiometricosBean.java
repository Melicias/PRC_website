package com.example.prc.ejbs;

import com.example.prc.entities.TipoDadosBiometricos;
import com.example.prc.entities.TipoProfissional;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class TipoDadosBiometricosBean {
    @PersistenceContext
    EntityManager em;

    public void create(String name, int min, int max, String quantitativo)
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

    public List<TipoDadosBiometricos> getAllTipoDadosBiometricos() {
        return (List<TipoDadosBiometricos>) em.createNamedQuery("getAllTipoDadosBiometricos").getResultList();
    }
}
