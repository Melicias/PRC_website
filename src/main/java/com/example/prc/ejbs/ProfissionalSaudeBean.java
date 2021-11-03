package com.example.prc.ejbs;

import com.example.prc.entities.ProfissionalSaude;
import com.example.prc.entities.TipoProfissional;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Stateless
public class ProfissionalSaudeBean {
    @PersistenceContext
    EntityManager em;

    public void create(String password, String name, String email, Date dataNasc, TipoProfissional tipoProfissional) throws MyEntityExistsException, MyEntityNotFoundException {
        ProfissionalSaude profissionalSaude=em.find(ProfissionalSaude.class,email);
        if(profissionalSaude!=null){
            throw new MyEntityExistsException("Profissional with email: "+email+" already exist");
        }
        if(em.find(TipoProfissional.class,tipoProfissional.getName())==null){
            throw new MyEntityNotFoundException("Tipo Profissional: "+tipoProfissional.getName()+ " not found");
        }
        ProfissionalSaude newprofissional= new ProfissionalSaude(password,name,email,tipoProfissional);
        em.persist(newprofissional);
    }
}
