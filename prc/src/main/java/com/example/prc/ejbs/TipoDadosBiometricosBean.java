package com.example.prc.ejbs;

import com.example.prc.dtos.TipoDadosBiometricosQuantitativoDTO;
import com.example.prc.entities.TipoDadosBiometricos;
import com.example.prc.entities.TipoDadosBiometricosQuantitativo;
import com.example.prc.entities.TipoProfissional;
import com.example.prc.entities.UtenteDadosBiometricos;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;
import com.example.prc.ws.LoginService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class TipoDadosBiometricosBean {
    @PersistenceContext
    EntityManager em;

    public void create(String name, double min, double max,boolean qualitativo, List<TipoDadosBiometricosQuantitativoDTO> tdbqDTOS)
            throws MyEntityExistsException, MyConstraintViolationException {
        TipoDadosBiometricos tipoDadosBiometricos = findTipoDadoBiometrico(name);
        if(tipoDadosBiometricos != null)
            throw new MyEntityExistsException("Name already in use!");

        try {
            TipoDadosBiometricos tipoDadoBiometrico;
            if(qualitativo) {
                tipoDadoBiometrico = new TipoDadosBiometricos(name, min, max);
                em.persist(tipoDadoBiometrico);
                em.flush();
            }else{
                for(int i = 1;i < tdbqDTOS.size()-1;i++){
                    if(!(tdbqDTOS.get(i).getMin() == 0 && tdbqDTOS.get(i).getMax() == 0 && tdbqDTOS.get(i).getName() == "")){
                        if(tdbqDTOS.get(i).getMin() == 0 || tdbqDTOS.get(i).getMax() == 0 || tdbqDTOS.get(i).getName() == ""){
                            throw new MyEntityExistsException("There is some type of error with number " + (i+1));
                        }
                    }
                }

                double valorAnterior = tdbqDTOS.isEmpty() ? 0 : tdbqDTOS.get(0).getMax();
                int posicao = 1;
                for(int i = 1;i < tdbqDTOS.size();i++){
                    if(!(tdbqDTOS.get(i).getMin() == 0 && tdbqDTOS.get(i).getMax() == 0 && tdbqDTOS.get(i).getName() == "")){
                        if(valorAnterior > tdbqDTOS.get(i).getMin()){
                            throw new MyEntityExistsException("The min value in " + (i+1) + " must be higher than the max in "+ (posicao));
                        }
                        valorAnterior = tdbqDTOS.get(i).getMax();
                        posicao = (i+1);
                    }
                }

                tipoDadoBiometrico = new TipoDadosBiometricos(name);
                em.persist(tipoDadoBiometrico);
                em.flush();

                if(tdbqDTOS.isEmpty())
                    return;

                TipoDadosBiometricosQuantitativo tdbq = new TipoDadosBiometricosQuantitativo(0,tdbqDTOS.get(0).getMax(),tdbqDTOS.get(0).getName(),tipoDadoBiometrico);
                tipoDadoBiometrico.addTipoDadosBiometricosQuantitativo(tdbq);
                em.persist(tdbq);
                em.merge(tipoDadoBiometrico);
                em.flush();

                tdbq = new TipoDadosBiometricosQuantitativo(tdbqDTOS.get(1).getMin(),tdbqDTOS.get(1).getMax(),tdbqDTOS.get(1).getName(),tipoDadoBiometrico);
                tipoDadoBiometrico.addTipoDadosBiometricosQuantitativo(tdbq);
                em.persist(tdbq);
                em.merge(tipoDadoBiometrico);
                em.flush();

                for(int i = 2;i < tdbqDTOS.size()-1;i++){
                    if(!(tdbqDTOS.get(i).getMin() == -1 || tdbqDTOS.get(i).getMax() == -1 || tdbqDTOS.get(i).getName() == "")){
                        tdbq = new TipoDadosBiometricosQuantitativo(tdbqDTOS.get(i).getMin(),tdbqDTOS.get(i).getMax(),tdbqDTOS.get(i).getName(),tipoDadoBiometrico);
                        tipoDadoBiometrico.addTipoDadosBiometricosQuantitativo(tdbq);
                        em.persist(tdbq);
                        em.merge(tipoDadoBiometrico);
                        em.flush();
                    }
                }
                tdbq = new TipoDadosBiometricosQuantitativo(tdbqDTOS.get(5).getMin(),0,tdbqDTOS.get(5).getName(),tipoDadoBiometrico);
                tipoDadoBiometrico.addTipoDadosBiometricosQuantitativo(tdbq);
                em.persist(tdbq);
                em.merge(tipoDadoBiometrico);
                em.flush();
            }
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public void update(int id, double min, double max)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        TipoDadosBiometricos tdb = em.find(TipoDadosBiometricos.class,id);
        if(tdb == null)
            throw new MyEntityNotFoundException("Name already in use!");

        try {
            if(tdb.getType() == 2) {
                tdb.setMin(min);
                tdb.setMax(max);
            }else{
                //FALTA FAZER quantitavito
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

    public List<TipoDadosBiometricos> getAllTipoDadosBiometricosNotDeleted() {
        return (List<TipoDadosBiometricos>) em.createNamedQuery("getAllTipoDadosBiometricosNotDeleted").getResultList();
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
