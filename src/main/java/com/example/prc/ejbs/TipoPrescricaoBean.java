package com.example.prc.ejbs;

import com.example.prc.entities.Admin;
import com.example.prc.entities.TipoPrescricao;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

@Stateless
public class TipoPrescricaoBean {
    @PersistenceContext
    EntityManager em;

    public void create(String name)
            throws MyEntityExistsException, MyConstraintViolationException {
        TipoPrescricao tipoPrescricao = em.find(TipoPrescricao.class, name);
        if(tipoPrescricao != null){
            throw new MyEntityExistsException("Name already in use: " + name);
        }

        try {
            tipoPrescricao = new TipoPrescricao(name);
            em.persist(tipoPrescricao);
        } catch (
                ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }
}
