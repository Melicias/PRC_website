package com.example.prc.ejbs;

import com.example.prc.entities.ProfissionalSaude;
import com.example.prc.entities.TipoDadosBiometricos;
import com.example.prc.entities.TipoProfissional;
import com.example.prc.entities.Utente;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class ProfissionalSaudeBean {
    @PersistenceContext
    EntityManager em;

    public void create(String password, String name, String email, int idTipoProfissional)
            throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        ProfissionalSaude profissionalSaude=em.find(ProfissionalSaude.class,email);
        if(profissionalSaude!=null)
            throw new MyEntityExistsException("Profissional with email: "+email+" already exist");

        TipoProfissional tipoProfissional = em.find(TipoProfissional.class,idTipoProfissional);
        if(tipoProfissional==null)
            throw new MyEntityNotFoundException("Tipo Profissional with the code: "+idTipoProfissional+ " not found");
        try {
            ProfissionalSaude newprofissional= new ProfissionalSaude(password,name,email,tipoProfissional);
            em.persist(newprofissional);
            em.flush();
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }
    public List<ProfissionalSaude> getAllProfissionalSaude() {
        return (List<ProfissionalSaude>) em.createNamedQuery("getAllProfissionalSaude").getResultList();
    }

    public void addUtente(String email, String emailUtente)
            throws MyEntityExistsException, MyConstraintViolationException, MyEntityNotFoundException {
        Utente utente = em.find(Utente.class, emailUtente);
        if (utente == null)
            throw new MyEntityExistsException();
        ProfissionalSaude profissionalSaude = em.find(ProfissionalSaude.class, email);
        if(profissionalSaude == null)
            throw new MyEntityNotFoundException();
        try {
            profissionalSaude.addUtente(utente);
            em.persist(profissionalSaude);
            em.flush();
        }catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }
    }
}
