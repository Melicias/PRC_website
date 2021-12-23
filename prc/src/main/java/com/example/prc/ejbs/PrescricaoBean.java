package com.example.prc.ejbs;

import com.example.prc.entities.Prescricao;
import com.example.prc.entities.TipoPrescricao;
import com.example.prc.entities.Utente;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.Date;

@Stateless
public class PrescricaoBean {
    @PersistenceContext
    EntityManager em;

    public void create(String descricao, String name, int idTipoPrescricao)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        TipoPrescricao tipoPrescricao = em.find(TipoPrescricao.class,idTipoPrescricao);
        if(tipoPrescricao == null)
            throw new MyEntityNotFoundException("TipoPrescricao with code: " + idTipoPrescricao + " not found.");
        try {
            Prescricao prescricao = new Prescricao(descricao,name,tipoPrescricao);
            em.persist(prescricao);
            em.flush();
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }
}
