package com.example.prc.ejbs;

import com.example.prc.dtos.ProfissionalSaudeDTO;
import com.example.prc.dtos.UtenteDTO;
import com.example.prc.entities.*;
import com.example.prc.exceptions.*;
import com.example.prc.ws.LoginService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;

import static io.smallrye.config.ConfigLogging.log;
import java.util.Locale;

@Stateless
public class UtenteBean {
    @PersistenceContext
    EntityManager em;

    public Utente authenticate(final String email, final String password) throws
            Exception {
        Utente utente = em.find(Utente.class, email);
        if (utente != null && utente.getPassword().equals(Utente.hashPassword(password))) {
            if(utente.getBlocked() == 0 && utente.getDeleted_at() == null)
                return utente;
            throw new Exception("Failed logging in with the email  '" + email + "': unknown email or wrong password");
        }
        throw new Exception("This account is blocked or deleted, contact and admi if you think this is an error");
    }
    public List<Utente> getAllUtentes() {
        return (List<Utente>) em.createNamedQuery("getAllUtentes").getResultList();
    }

    public Utente findUtente(String email){
        return (Utente) em.createNamedQuery("getUtente").setParameter("email",email).getSingleResult();
    }

    public void create(String password, String name, String email, Date dataNasc, String emailProfissionalSaude)
            throws MyEntityExistsException, MyConstraintViolationException, MyEntityNotFoundException, MyIllegalArgumentException {
        Utente utente = em.find(Utente.class, email);
        if(utente != null)
            throw new MyEntityExistsException("This email is already in use.");

        ProfissionalSaude profissionalSaude = em.find(ProfissionalSaude.class, emailProfissionalSaude);
        if(profissionalSaude == null)
            throw new MyEntityNotFoundException("The healthcare specialist does not exist.");

        if(dataNasc.after(new Date()))
            throw new MyIllegalArgumentException("The birthday date is invalid.");

        try {
            Utente newUtente = new Utente(password, name, email, dataNasc);
            profissionalSaude.addUtente(newUtente);
            newUtente.addProfissionalSaude(profissionalSaude);
            em.persist(newUtente);
            em.flush();
        }catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public void create(String password, String name, String email, Date dataNasc)
            throws MyEntityExistsException, MyConstraintViolationException, MyEntityNotFoundException {
        Utente utente = em.find(Utente.class, email);
        if(utente != null)
            throw new MyEntityExistsException();
        try {
            Utente newUtente = new Utente(password, name, email, dataNasc);
            em.persist(newUtente);
            em.flush();
        }catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public List<Utente> getUtentesSemProfissionalSaude(String profissionalEmail)
            throws MyEntityNotFoundException {
        try{
            ProfissionalSaude ps = em.find(ProfissionalSaude.class, profissionalEmail);
            if(ps == null)
                throw new MyEntityNotFoundException("Healthcare specialists not found.");

            return (List<Utente>) em.createNamedQuery("getUtenteSemProfissional").setParameter("email",profissionalEmail).getResultList();
        }catch (Exception e){
            return null;
        }
    }
    public List<Utente> getUtentesComProfissionalSaude(String profissionalEmail)
            throws MyEntityNotFoundException {
        try{
            ProfissionalSaude ps = em.find(ProfissionalSaude.class, profissionalEmail);
            if(ps == null)
                throw new MyEntityNotFoundException("Healthcare specialists not found.");

            return (List<Utente>) em.createNamedQuery("getUtenteComProfissional").setParameter("email",profissionalEmail).getResultList();
        }catch (Exception e){
            return null;
        }
    }


    public void updateUtente(String email,String name, String password, Date birthDate)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        Utente utente = em.find(Utente.class, email);
        if(utente == null)
            throw new MyEntityNotFoundException("Patient with this email not found");

        try {
            em.lock(utente, LockModeType.OPTIMISTIC);
            utente.setName(name);
            if(password != null)
                utente.setPassword(password);
            if(birthDate != null)
                utente.setDataNasc(birthDate);
            em.persist(utente);
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Utente blockUtente(String email)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        Utente u = em.find(Utente.class,email);
        if(u == null)
            throw new MyEntityNotFoundException("Patient with this email not found");
        List<Prc> prcs = u.getPrcs();
        for(int i = 0; i < prcs.size(); i++){
            if(prcs.get(i).getValidade().after(new Date()))
                throw new MyEntityNotFoundException("There is active PRCS, the user can't be blocked");
        }
        if(u.getBlocked() == 0){
            u.setBlocked(1);
        }else{
            u.setBlocked(0);
        }
        em.persist(u);
        em.flush();

        return u;
    }

    public Utente deleteUtente(String email)
        throws MyEntityNotFoundException, MyConstraintViolationException {
        Utente u = em.find(Utente.class,email);
        if(u == null)
            throw new MyEntityNotFoundException("Patient with this email not found");
        if (u.getDadosBiometricos().isEmpty() && u.getProfissionalSaude().isEmpty() && u.getPrcs().isEmpty()) {
            em.remove(u);
            em.flush();
            return null;
        }
        if(u.getDeleted_at() == null){
            u.setDeleted_at(new Date());
        }else{
            u.setDeleted_at(null);
        }
        em.persist(u);
        em.flush();
        return u;
    }

}
