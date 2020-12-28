package com.olivercormier.sidebracketapi.security;

import com.olivercormier.sidebracketapi.users.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class LoginController {
/*
    @GetMapping("/login")
    ResponseEntity<Object> login(@RequestParam(name = "email") String email,
                                 @RequestParam(name = "password") String password) {
        UserDao users = new UserDao();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");

        Collection<User> allUsers = users.getAll();
        Predicate<User> streamsUser = item -> item.getEmail().equals(email);
        List<User> results = allUsers.stream().filter(streamsUser).collect(Collectors.toList());

        if (results.size() == 1) {
            User user = results.get(0);
            if (user.getPassword().equals(password)) {
                return new ResponseEntity<>("", headers, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Invalid Credentials", headers, HttpStatus.UNAUTHORIZED);
    } */
}
