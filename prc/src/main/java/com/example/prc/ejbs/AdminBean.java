package com.example.prc.ejbs;

import com.example.prc.entities.Admin;
import com.example.prc.entities.ProfissionalSaude;
import com.example.prc.entities.User;
import com.example.prc.entities.Utente;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;
import com.example.prc.exceptions.MyIllegalArgumentException;
import com.nimbusds.jose.shaded.json.JSONObject;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import java.util.List;

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

    public List<Admin> getAllAdmins() {
        return (List<Admin>) em.createNamedQuery("getAllAdmins").getResultList();
    }

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
        if(admin == null)
            throw new MyEntityNotFoundException("No admin found with the email: " + email);

        try {
            em.lock(admin, LockModeType.OPTIMISTIC);
            admin.setName(name);
            admin.setPassword(password);
            em.persist(admin);
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public void updatePassword(String email, String password, String newPassword, String confirmPassword)
            throws MyConstraintViolationException, MyEntityNotFoundException, MyIllegalArgumentException {
        Admin admin = em.find(Admin.class,email);
        if(admin == null) throw new MyEntityNotFoundException("Admin with email: "+email+" doesnt exist");
        if (!User.hashPassword(password).equals(admin.getPassword()))
            throw new MyIllegalArgumentException("Old password is not valid");
        if (newPassword.length() < 8)
            throw new MyIllegalArgumentException("New password is not valid, must has at least 8 characters");
        if (!newPassword.equals(confirmPassword))
            throw new MyIllegalArgumentException("Confirm password is not valid");
        try {
            admin.setPassword(newPassword);
            em.persist(admin);
            em.flush();
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Admin findAdmin(String email) throws MyEntityNotFoundException{
        Admin admin = em.find(Admin.class, email);
        if(admin == null)
            throw new MyEntityNotFoundException();
        return admin;
    }

    public Response getStatistics(List<Utente> utentes, List<ProfissionalSaude> ps){
        int utentesBloqueados = 0;
        int utentesApagados = 0;
        int PSBloqueados = 0;
        int PSApagados = 0;
        try{
            for (Utente utente: utentes) {
                if(utente.getDeleted_at() != null)
                    utentesApagados++;
                if(utente.getBlocked() ==1)
                    utentesBloqueados++;
            }
            for (ProfissionalSaude profissionalSaude: ps) {
                if(profissionalSaude.getDeleted_at() != null)
                    PSApagados++;
                if(profissionalSaude.getBlocked() ==1)
                    PSBloqueados++;
            }

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("nrUtentes", utentes.size());
            jsonObj.put("utentesBloqueados", utentesBloqueados);
            jsonObj.put("utentesApagados", utentesApagados);
            jsonObj.put("nrPS", ps.size());
            jsonObj.put("PSBloqueados", PSBloqueados);
            jsonObj.put("PSApagados", PSApagados);

            return Response.ok(jsonObj.toString()).build();
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    public Admin blockAdmin(String email) throws MyEntityNotFoundException {
        Admin admin = em.find(Admin.class,email);
        if(admin == null)
            throw new MyEntityNotFoundException();
        if(admin.getBlocked() == 0){
            admin.setBlocked(1);
        }else{
            admin.setBlocked(0);
        }
        em.persist(admin);
        em.flush();
        return admin;
    }

}
