package com.example.prc.ejbs;

import com.example.prc.dtos.TipoDadosBiometricosQuantitativoDTO;
import com.example.prc.entities.TipoDadosBiometricos;
import com.example.prc.entities.TipoDadosBiometricosQuantitativo;
import com.example.prc.entities.UtenteDadosBiometricos;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class TipoDadosBiometricosQuantitativoBean {
    @PersistenceContext
    EntityManager em;

    public void create(String name, double min, double max, int tipoDadosBiometricosId)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        TipoDadosBiometricos tipoDadosBiometricos = em.find(TipoDadosBiometricos.class,tipoDadosBiometricosId);
        if(tipoDadosBiometricos == null) throw new MyEntityNotFoundException("Not found the Tipo dados biometricos");
        try {
            TipoDadosBiometricosQuantitativo tdbq = new TipoDadosBiometricosQuantitativo(min,max,name,tipoDadosBiometricos);
            tipoDadosBiometricos.addTipoDadosBiometricosQuantitativo(tdbq);
            em.persist(tdbq);
            em.merge(tipoDadosBiometricos);
            em.flush();
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public List<TipoDadosBiometricosQuantitativo> find(int tipoDadoBiometrico_id) throws MyEntityNotFoundException {
        try {
            TipoDadosBiometricos tipoDadosBiometricos = em.find(TipoDadosBiometricos.class, tipoDadoBiometrico_id);
            if (tipoDadosBiometricos == null) {
                throw new MyEntityNotFoundException("Cant find this utente");
            }
            return (List<TipoDadosBiometricosQuantitativo>) em.createNamedQuery("tipodadosBiometrico").setParameter("id", tipoDadoBiometrico_id).getResultList();
        } catch (Exception e) {
            return  null;
        }
    }
}
