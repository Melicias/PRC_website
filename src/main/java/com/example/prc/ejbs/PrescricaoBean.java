package com.example.prc.ejbs;

import com.example.prc.entities.Admin;
import com.example.prc.entities.Prescricao;
import com.example.prc.entities.TipoPrescricao;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;
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

    public void create(String descricao, Date validade, int idTipoPrescricao)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        TipoPrescricao tipoPrescricao = em.find(TipoPrescricao.class,idTipoPrescricao);
        if(tipoPrescricao == null){
            throw new MyEntityNotFoundException("TipoPrescricao with code: " + idTipoPrescricao + " not found.");
        }
        try {
            Prescricao prescricao;
            if(validade == null){
                prescricao = new Prescricao(descricao,tipoPrescricao);
            }else{
                prescricao = new Prescricao(descricao,validade,tipoPrescricao);
            }

            em.persist(prescricao);
        } catch (
                ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }
}
