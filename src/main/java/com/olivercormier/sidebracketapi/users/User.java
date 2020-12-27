package com.olivercormier.sidebracketapi.users;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Data
//@Repository
//@Document(collection = "Users")
public class User {

    @Id
    public String id;

    public String username, password, email;
    //public boolean locked, passwordReset;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        //this.locked = false;
        //this.passwordReset = false;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, username='%s', password='%s', email='%s']",
                id, username, password, email);
    }
}
