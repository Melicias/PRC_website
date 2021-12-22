package com.example.prc.ejbs;

import com.example.prc.entities.Admin;
import com.example.prc.entities.Utente;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

@Stateless
public class AdminBean {
    @PersistenceContext
    EntityManager em;

    public Admin authenticate(final String email, final String password) throws
            Exception {
        Admin admin = em.find(Admin.class, email);
        if (admin != null && admin.getPassword().equals(Admin.hashPassword(password))) {
            return admin;
        }
        throw new Exception("Failed logging in with the email  '" + email + "': unknown email or wrong password");
    }

    //https://www.javatpoint.com/how-to-encrypt-password-in-java
    public void create(String password, String name, String email)
            throws MyEntityExistsException, MyConstraintViolationException {
        Admin admin = em.find(Admin.class, email);
        if(admin != null)
            throw new MyEntityExistsException("Email already in use for admin: " + email);
        try {
            admin = new Admin(password, name, email);
            em.persist(admin);
            em.flush();
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public void update(String password, String name, String email)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        Admin admin = em.find(Admin.class, email);
        if(admin == null){
            throw new MyEntityNotFoundException("No admin found with the email: " + email);
        }

        try {
            em.lock(admin, LockModeType.OPTIMISTIC);
            admin.setName(name);
            admin.setPassword(password);
            em.persist(admin);
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Admin findAdmin(String email)
            throws MyEntityNotFoundException{
        Admin admin = em.find(Admin.class, email);
        if(admin == null)
            throw new MyEntityNotFoundException();
        return admin;
    }

    public void remove(Admin admin) {
        Admin adminMerged = em.merge(admin);
        em.remove(adminMerged);
    }

}
