package com.example.prc.ejbs;

import com.example.prc.dtos.UtenteDTO;
import com.example.prc.entities.ProfissionalSaude;
import com.example.prc.entities.TipoDadosBiometricos;
import com.example.prc.entities.TipoProfissional;
import com.example.prc.entities.Utente;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;

@Stateless
public class ProfissionalSaudeBean {
    @PersistenceContext
    EntityManager em;

    public void create(String password, String name, String email, int idTipoProfissional)
            throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        ProfissionalSaude profissionalSaude=em.find(ProfissionalSaude.class,email);
        if(profissionalSaude!=null) throw new MyEntityExistsException("Profissional with email: "+email+" already exist");

        TipoProfissional tipoProfissional = em.find(TipoProfissional.class,idTipoProfissional);
        if(tipoProfissional==null) throw new MyEntityNotFoundException("Tipo Profissional with the code: "+idTipoProfissional+ " not found");
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

    public ProfissionalSaude findTipoProfissional(String email) {
        try{
            return (ProfissionalSaude) em.createNamedQuery("getProfissionalSaude").setParameter("email", email).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    public void update(String email, String name, String password, int idTipoProfissional)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        TipoProfissional tipoProfissional = em.find(TipoProfissional.class, idTipoProfissional);
        if(tipoProfissional == null)
            throw new MyEntityNotFoundException("The specialist type was not found, try with other.");
        ProfissionalSaude profissionalSaude = em.find(ProfissionalSaude.class,email);
        if(profissionalSaude == null)
            throw new MyEntityNotFoundException("The Healthcare specialist was not found..");
        try {
            em.lock(profissionalSaude, LockModeType.OPTIMISTIC);
            profissionalSaude.setName(name);
            if(password != null)
                profissionalSaude.setPassword(password);
            profissionalSaude.setTipoProfissional(tipoProfissional);
            em.persist(profissionalSaude);
        } catch (
                ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public ProfissionalSaude deleteProfissionalSaude(String email){
        ProfissionalSaude ps = em.find(ProfissionalSaude.class,email);
        if(ps != null){
            if(ps.getDeleted_at() == null){
                ps.setDeleted_at(new Date());
            }else{
                ps.setDeleted_at(null);
            }
            em.persist(ps);
            em.flush();
        }
        return ps;
    }

    public ProfissionalSaude blockProfissionalSaude(String email){
        ProfissionalSaude ps = em.find(ProfissionalSaude.class,email);
        if(ps != null){
            if(ps.getBlocked() == 0){
                ps.setBlocked(1);
            }else{
                ps.setBlocked(0);
            }
            em.persist(ps);
            em.flush();
        }
        return ps;
    }

    public Utente addUtente(String emailprofissional, UtenteDTO utenteDTO)
            throws MyEntityNotFoundException, MyConstraintViolationException, MyEntityExistsException {
        ProfissionalSaude profissionalSaude = em.find(ProfissionalSaude.class,emailprofissional);
        if(profissionalSaude == null)
            throw new MyEntityNotFoundException("The Healthcare specialist was not found..");
        Utente utente = em.find(Utente.class,utenteDTO.getEmail());
        if(utente == null)
            throw new MyEntityNotFoundException("The Pacient was not found..");
        List<ProfissionalSaude> ps = utente.getProfissionalSaude();
        for(int i = 0; i <ps.size();i++){
            if(ps.get(i).getEmail() == emailprofissional){
                throw new MyEntityExistsException("This connection already exists...");
            }
        }
        try {
            profissionalSaude.addUtente(utente);
            utente.addProfissionalSaude(profissionalSaude);
            em.merge(utente);
            em.merge(profissionalSaude);
            return utente;
        } catch (
                ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Utente removeUtente(String emailprofissional, UtenteDTO utenteDTO)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        ProfissionalSaude profissionalSaude = em.find(ProfissionalSaude.class,emailprofissional);
        if(profissionalSaude == null)
            throw new MyEntityNotFoundException("The Healthcare specialist was not found..");
        Utente utente = em.find(Utente.class,utenteDTO.getEmail());
        if(utente == null)
            throw new MyEntityNotFoundException("The Pacient was not found..");
        try {
            profissionalSaude.removerUtente(utente);
            utente.removeProfissionalSaude(profissionalSaude);
            em.merge(utente);
            em.merge(profissionalSaude);
            return utente;
        } catch (
                ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public List<ProfissionalSaude> getProfissionaisSemUtente(String emailUtente)
            throws MyEntityNotFoundException {
        try{
            Utente u = em.find(Utente.class, emailUtente);
            if(u == null)
                throw new MyEntityNotFoundException();
            return (List<ProfissionalSaude>) em.createNamedQuery("getProfissionaisSemEsteUtente").setParameter("email",emailUtente).getResultList();
        }catch (Exception e){
            return null;
        }
    }
}
