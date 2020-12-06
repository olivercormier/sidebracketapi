package com.olivercormier.sidebracketapi.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UserController {

    private UserDao users;

    public UserController() {
        this.users = new UserDao();
    }

    @GetMapping("/users")
    Collection<User> all() {
        return users.getAll();
    }
}
