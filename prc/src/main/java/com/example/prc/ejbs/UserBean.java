package com.example.prc.ejbs;

import com.example.prc.entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateless
public class UserBean {
    @PersistenceContext
    EntityManager em;
    public User authenticate(final String email, final String password) throws
            Exception {
        User user = em.find(User.class, email);
        if (user != null && user.getPassword().equals(User.hashPassword(password))) {
            return user;
        }
        throw new Exception("Failed logging in with the email  '" + email + "': unknown email or wrong password");
    }
}


