package com.example.prc.entities;

import javax.persistence.Entity;

@Entity
public class Admin extends User{

    public Admin() {
    }

    public Admin(String password, String name, String email) {
        super(password, name, email);
    }
}
