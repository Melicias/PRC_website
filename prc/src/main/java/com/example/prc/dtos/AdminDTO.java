package com.example.prc.dtos;

import java.util.Date;

public class AdminDTO {
    private String email;
    private String password;
    private String name;
    private Date deleted_at;
    private int blocked;

    public AdminDTO() {
    }

    public AdminDTO(String email, String password, String name, Date deleted_at, int blocked) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.deleted_at = deleted_at;
        this.blocked = blocked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public int getBlocked() {
        return blocked;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }
}
