package com.example.prc.ejbs;

import com.example.prc.dtos.UtenteDTO;
import com.example.prc.entities.*;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;
import com.example.prc.exceptions.MyIllegalArgumentException;
import com.example.prc.ws.LoginService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ProfissionalSaudeBean {
    @PersistenceContext
    EntityManager em;

    private static final Logger log =
            Logger.getLogger(ProfissionalSaude.class.getName());

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
            tipoProfissional.addProfissionaisSaude(newprofissional);
            em.persist(newprofissional);
            em.flush();
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public void updatePassword(String email, String password, String newPassword, String confirmPassword)
            throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {
        ProfissionalSaude profissionalSaude = em.find(ProfissionalSaude.class,email);
        if(profissionalSaude == null) throw new MyEntityExistsException("Profissional with email: "+email+" doesnt exist");
        if (!User.hashPassword(password).equals(profissionalSaude.getPassword()))
            throw new MyIllegalArgumentException("Old password is not valid");
        if (newPassword.length() < 8)
            throw new MyIllegalArgumentException("New password is not valid, must has at least 8 characters");
        if (!newPassword.equals(confirmPassword))
            throw new MyIllegalArgumentException("Confirm password is not valid");
        try {
            profissionalSaude.setPassword(newPassword);
            em.persist(profissionalSaude);
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
            if(profissionalSaude.getTipoProfissional().getId() != idTipoProfissional){
                TipoProfissional tipoProfissionalAntigo =  profissionalSaude.getTipoProfissional();
                tipoProfissionalAntigo.removeProfissionaisSaude(profissionalSaude);
                profissionalSaude.setTipoProfissional(tipoProfissional);
                tipoProfissional.addProfissionaisSaude(profissionalSaude);
            }
            profissionalSaude.setName(name);
            if(password != null)
                profissionalSaude.setPassword(password);
            em.persist(profissionalSaude);
        } catch (
                ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public ProfissionalSaude deleteProfissionalSaude(String email)
            throws MyEntityNotFoundException {
        ProfissionalSaude ps = em.find(ProfissionalSaude.class,email);
        if(ps == null)
            throw new MyEntityNotFoundException("Patient with this email not found");

        if(ps.getUtentes().isEmpty() && ps.getPrcs().isEmpty()){
            em.remove(ps);
            em.flush();
            return null;
        }
        if(ps.getDeleted_at() == null){
            ps.setDeleted_at(new Date());
        }else{
            ps.setDeleted_at(null);
        }
        em.persist(ps);
        em.flush();
        return ps;
    }

    public ProfissionalSaude blockProfissionalSaude(String email) throws MyEntityNotFoundException{
        ProfissionalSaude ps = em.find(ProfissionalSaude.class,email);
        if(ps == null)
            throw new MyEntityNotFoundException("The Healthcare specialist was not found.");

        if(ps.getBlocked() == 0){
            ps.setBlocked(1);
        }else{
            ps.setBlocked(0);
        }
        em.persist(ps);
        em.flush();

        return ps;
    }

    public Utente addUtente(String emailprofissional, String emailUtente)
            throws MyEntityNotFoundException, MyConstraintViolationException, MyEntityExistsException {
        ProfissionalSaude profissionalSaude = em.find(ProfissionalSaude.class,emailprofissional);
        if(profissionalSaude == null)
            throw new MyEntityNotFoundException("The Healthcare specialist was not found.");
        Utente utente = em.find(Utente.class, emailUtente);
        if(utente == null)
            throw new MyEntityNotFoundException("The Patient was not found.");
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

    public Utente removeUtente(String emailprofissional, String emailUtente)
            throws MyEntityNotFoundException, MyConstraintViolationException,MyIllegalArgumentException {
        ProfissionalSaude profissionalSaude = em.find(ProfissionalSaude.class,emailprofissional);
        if(profissionalSaude == null)
            throw new MyEntityNotFoundException("The Healthcare specialist was not found..");
        Utente utente = em.find(Utente.class,emailUtente);
        if(utente == null)
            throw new MyEntityNotFoundException("The Pacient was not found..");
        List<Prc> prcs = utente.getPrcs();
        for(int i = 0;i <prcs.size();i++){
            if(prcs.get(i).getProfissionalSaude().getEmail() == profissionalSaude.getEmail()){
                if (prcs.get(i).getValidade().after(new Date())) {
                    throw new MyIllegalArgumentException("There is Prcs not finished...");
                }
            }
        }
        try {
            profissionalSaude.removerUtente(utente);
            utente.removeProfissionalSaude(profissionalSaude);
            em.merge(utente);
            em.merge(profissionalSaude);
            return utente;
        } catch (ConstraintViolationException e) {
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
