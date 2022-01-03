package com.example.prc.ejbs;

import com.example.prc.entities.*;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;

@Stateless
public class PrescricaoBean {
    @PersistenceContext
    EntityManager em;

    public void create(String descricao, String name, int idTipoPrescricao)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        TipoPrescricao tipoPrescricao = em.find(TipoPrescricao.class, idTipoPrescricao);
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

    public void update(int idPrescricao, String descricao, String name, int idTipoPrescricao)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        Prescricao prescricao = em.find(Prescricao.class, idPrescricao);
        if(prescricao == null)
            throw new MyEntityNotFoundException("Prescription was not found");

        TipoPrescricao tipoPrescricao = em.find(TipoPrescricao.class, idTipoPrescricao);
        if(tipoPrescricao == null)
            throw new MyEntityNotFoundException("Prescription type with code: " + idTipoPrescricao + " was not found.");

        try {
            if (name != null)
                prescricao.setName(name);
            if(descricao != null)
                prescricao.setDescricao(descricao);
            prescricao.setTipoPrescricao(tipoPrescricao);
            em.persist(prescricao);
            em.flush();
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Prescricao delete(int idPrescricao)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        Prescricao prescricao = em.find(Prescricao.class, idPrescricao);
        if(prescricao == null)
            throw new MyEntityNotFoundException("Prescription not found");

        if (prescricao.getPrcs().size() == 0) {
            em.remove(prescricao);
            em.flush();
            return prescricao;
        }
        if(prescricao.getDeleted_at() == null){
            prescricao.setDeleted_at(new Date());
        }else{
            prescricao.setDeleted_at(null);
        }
        em.persist(prescricao);
        em.flush();
        return prescricao;
    }

    public List<Prescricao> getAllPrescricoes() {
        return (List<Prescricao>) em.createNamedQuery("getAllPrescricoes").getResultList();
    }

    public List<Prescricao> getPrescricoesComUmPrc(int idPrc) throws MyEntityNotFoundException {
        Prc prc = em.find(Prc.class, idPrc);
        if(prc == null)
            throw new MyEntityNotFoundException("The PRC was not found.");
        return (List<Prescricao>) em.createNamedQuery("getPrescricoesComUmPrc").setParameter("idPrc", idPrc).getResultList();
    }

    public List<Prescricao> getPrescricoesSemUmPrc(int idPrc)
            throws MyEntityNotFoundException {
        Prc prc = em.find(Prc.class, idPrc);
        if(prc == null)
            throw new MyEntityNotFoundException();
        return (List<Prescricao>) em.createNamedQuery("getPrescricoesSemUmPrc").setParameter("idPrc", idPrc).getResultList();
    }

}
