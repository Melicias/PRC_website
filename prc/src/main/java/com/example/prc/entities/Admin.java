package com.example.prc.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllAdmins",
                query = "select  admins FROM Admin admins order by  admins.email"
        )
})
public class Admin extends User{

    public Admin() {
    }

    public Admin(String password, String name, String email) {
        super(password, name, email);
    }
}
