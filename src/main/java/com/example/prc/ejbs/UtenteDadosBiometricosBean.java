package com.example.prc.ejbs;

import com.example.prc.entities.TipoDadosBiometricos;
import com.example.prc.entities.User;
import com.example.prc.entities.Utente;
import com.example.prc.entities.UtenteDadosBiometricos;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Stateless
public class UtenteDadosBiometricosBean {
    @PersistenceContext
    EntityManager em;

    public void create(TipoDadosBiometricos tipoDadosBiometricos, Date data_observacao, String valor, Utente utente) throws MyEntityNotFoundException {
        if(em.find(Utente.class,utente.getEmail())==null){
            throw new MyEntityNotFoundException("Utente :"+ utente.getName()+"não foi encontrado");
        }
        if(em.find(TipoDadosBiometricos.class,tipoDadosBiometricos.getName())==null){
            throw new MyEntityNotFoundException("Tipo Dados Biometricos : "+tipoDadosBiometricos.getName()+" não foi encontrado");
        }
        UtenteDadosBiometricos newutenteDadosBiometricos= new UtenteDadosBiometricos(tipoDadosBiometricos,data_observacao,valor);
        utente.addDadosBiometricos(newutenteDadosBiometricos);
        em.persist(newutenteDadosBiometricos);
    }
}
