package com.example.prc.ejbs;

import com.example.prc.dtos.UtenteDadosBiometricosDTO;
import com.example.prc.entities.TipoDadosBiometricos;
import com.example.prc.entities.Utente;
import com.example.prc.entities.UtenteDadosBiometricos;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;

import static io.smallrye.config.ConfigLogging.log;

@Stateless
public class UtenteDadosBiometricosBean {
    @PersistenceContext
    EntityManager em;

    public void create(int idTipoDadosBiometricos, Date data_observacao, String valor, String utenteEmail)
            throws MyEntityNotFoundException, MyEntityExistsException,MyConstraintViolationException{
        Utente utente = em.find(Utente.class,utenteEmail);
        if(utente==null)
            throw new MyEntityExistsException("Utente :"+ utenteEmail +"não foi encontrado");
        TipoDadosBiometricos tipoDadosBiometricos = em.find(TipoDadosBiometricos.class,idTipoDadosBiometricos);
        if(tipoDadosBiometricos==null)
            throw new MyEntityNotFoundException("Tipo Dados Biometricos : "+ idTipoDadosBiometricos +" não foi encontrado");

        try{
            UtenteDadosBiometricos newutenteDadosBiometricos = new UtenteDadosBiometricos(tipoDadosBiometricos,data_observacao,valor, utente);
            utente.addDadosBiometricos(newutenteDadosBiometricos);
            em.persist(newutenteDadosBiometricos);
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }

    }

    public List<UtenteDadosBiometricos> findMax(String email) throws MyEntityNotFoundException {
        try {
            Utente utente = em.find(Utente.class, email);
            if (utente == null)
                throw new MyEntityNotFoundException("Cant find this utente");
            log.info(email);
            return (List<UtenteDadosBiometricos>) em.createNamedQuery("dadosBiometricos").setParameter("email",email).getResultList();

        } catch (Exception e) {
            return null;
        }
    }
}
