package com.olivercormier.sidebracketapi.users;

import lombok.Data;

@Data
public class User {
    public int id;
    public String username, password, email;
    public boolean locked, passwordReset;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.locked = false;
        this.passwordReset = false;
    }
}
