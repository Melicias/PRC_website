package com.example.prc.ejbs;

import com.example.prc.entities.TipoDadosBiometricos;
import com.example.prc.entities.TipoProfissional;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class TipoProfissionalBean {
    @PersistenceContext
    EntityManager em;

    public String create(String name)
            throws MyConstraintViolationException, MyEntityExistsException {
        TipoProfissional tp = findTipoProfissional(name);
        if(tp != null) throw new MyEntityExistsException("Name already in use!");
        try {
            TipoProfissional tipoProfissional = new TipoProfissional(name);
            em.persist(tipoProfissional);
            em.flush();
            return tipoProfissional.getId() + "";
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public void update(int id, String name)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        TipoProfissional tipoProfissional = em.find(TipoProfissional.class, id);
        if(tipoProfissional == null)
            throw new MyEntityNotFoundException("No TipoProfissional with the id: " + id);

        try {
            em.lock(tipoProfissional, LockModeType.OPTIMISTIC); //ou usar PESSIMISTIC_WRITE ???
            tipoProfissional.setName(name);
            em.persist(tipoProfissional);
        } catch (
                ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public void remove(TipoProfissional tipoProfissional) {
        TipoProfissional tipoProfissionalMerged = em.merge(tipoProfissional);
        em.remove(tipoProfissionalMerged);
    }

    public List<TipoProfissional> getAllTipoProfissional() {
        return (List<TipoProfissional>) em.createNamedQuery("getAllTipoProfisiional").getResultList();
    }

    public TipoProfissional findTipoProfissional(String name) {
        try{
            return (TipoProfissional) em.createNamedQuery("getTipoProfissionalByName").setParameter("name", name).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
}
