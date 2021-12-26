package com.example.prc.ejbs;

import com.example.prc.entities.ProfissionalSaude;
import com.example.prc.entities.User;
import com.example.prc.entities.Utente;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Stateless
public class UtenteBean {
    @PersistenceContext
    EntityManager em;

    public Utente authenticate(final String email, final String password) throws
            Exception {
        Utente utente = em.find(Utente.class, email);
        if (utente != null && utente.getPassword().equals(Utente.hashPassword(password))) {
            return utente;
        }
        throw new Exception("Failed logging in with the email  '" + email + "': unknown email or wrong password");
    }

    public Utente findUtente(String email){
        return em.find(Utente.class, email);
    }

    public void create(String password, String name, String email, Date dataNasc, String emailProfissionalSaude)
            throws MyEntityExistsException, MyConstraintViolationException, MyEntityNotFoundException {
        Utente utente = em.find(Utente.class, email);
        if(utente != null)
            throw new MyEntityExistsException();
        ProfissionalSaude profissionalSaude = em.find(ProfissionalSaude.class, emailProfissionalSaude);
        if(profissionalSaude == null)
            throw new MyEntityNotFoundException();
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
                throw new MyEntityNotFoundException();
            return (List<Utente>) em.createNamedQuery("getUtenteSemProfissional").setParameter("email",profissionalEmail).getResultList();
        }catch (Exception e){
            return null;
        }
    }
}
