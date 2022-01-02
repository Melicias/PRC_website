package com.example.prc.ejbs;

import com.example.prc.dtos.UtenteDadosBiometricosDTO;
import com.example.prc.entities.TipoDadosBiometricos;
import com.example.prc.entities.TipoDadosBiometricosQuantitativo;
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

    public UtenteDadosBiometricos create(int idTipoDadosBiometricos, Date data_observacao, double valor, String utenteEmail)
            throws MyEntityNotFoundException, MyEntityExistsException,MyConstraintViolationException{
        Utente utente = em.find(Utente.class,utenteEmail);
        if(utente==null)
            throw new MyEntityExistsException("Utente :"+ utenteEmail +"não foi encontrado");
        TipoDadosBiometricos tipoDadosBiometricos = em.find(TipoDadosBiometricos.class,idTipoDadosBiometricos);
        if(tipoDadosBiometricos==null)
            throw new MyEntityNotFoundException("Tipo Dados Biometricos : "+ idTipoDadosBiometricos +" não foi encontrado");

        try{
            log.info("OIOI");
            UtenteDadosBiometricos newutenteDadosBiometricos = new UtenteDadosBiometricos(tipoDadosBiometricos,data_observacao,valor, utente);
            utente.addDadosBiometricos(newutenteDadosBiometricos);
            if(newutenteDadosBiometricos.getTipoDadosBiometricos().getType()==2){
                if(newutenteDadosBiometricos.getValor() > newutenteDadosBiometricos.getTipoDadosBiometricos().getMax()){
                            newutenteDadosBiometricos.setAvaliacao("High");
                }
                else if(newutenteDadosBiometricos.getValor()< newutenteDadosBiometricos.getTipoDadosBiometricos().getMin()){
                    newutenteDadosBiometricos.setAvaliacao("Low");
                }else {
                    newutenteDadosBiometricos.setAvaliacao("Normal");
                }
            }else {
                TipoDadosBiometricosQuantitativoBean tipoDadosBiometricosQuantitativo=new TipoDadosBiometricosQuantitativoBean();
               List<TipoDadosBiometricosQuantitativo> tipoDadosBiometricosQuantitativos= tipoDadosBiometricosQuantitativo.find(newutenteDadosBiometricos.getTipoDadosBiometricos().getId());
                for (TipoDadosBiometricosQuantitativo dados: tipoDadosBiometricosQuantitativos
                     ) {
                    if(dados.getMin()<newutenteDadosBiometricos.getValor() && newutenteDadosBiometricos.getValor()<dados.getMax())
                    {
                        newutenteDadosBiometricos.setAvaliacao(dados.getName());
                    }else{
                        newutenteDadosBiometricos.setAvaliacao("Without appraisal");
                    }
                }

            }
            em.persist(newutenteDadosBiometricos);
            return newutenteDadosBiometricos;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }

    }

    public List<UtenteDadosBiometricos> find(String email) throws MyEntityNotFoundException {
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
