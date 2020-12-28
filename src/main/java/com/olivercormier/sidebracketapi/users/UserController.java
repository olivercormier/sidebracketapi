package com.olivercormier.sidebracketapi.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class UserController {

    //private UserDao users;

    private User checkUser;

    @Autowired
    public UserRepository userRepository;

    public UserController() {
        //this.users = new UserDao();
    }

    @GetMapping("/users/{id}")
    ResponseEntity<Object> read(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<>(result, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User Record Not Found", headers, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users")
    ResponseEntity<Object> all(@RequestParam(name = "email", required = false) String email) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");
        Collection<User> allUsers = userRepository.findAll();
        if (email != null && !email.trim().isEmpty()) {
            Predicate<User> streamsUser = item -> item.getEmail().equals(email);
            return new ResponseEntity<>(allUsers.stream().filter(streamsUser).collect(Collectors.toList()),
                    headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(allUsers, headers, HttpStatus.OK);
        }
    }

    @PostMapping("/users")
    ResponseEntity<Object> create(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(userRepository.save(user)).toUri();
        headers.setLocation(location);
        System.out.println(user.toString());
        checkUser = userRepository.save(user);
        if (checkUser != null) {
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/users/{id}")
    ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody User user) {
        //user.setId(Integer.parseInt(id));
        //users.update(user);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<Object> delete(@PathVariable("id") String id) {
        //users.delete(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/auth")
    ResponseEntity<Object> login(@RequestParam(name = "email") String email,
                                 @RequestParam(name = "password") String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");

        System.out.println("email: " + email + ", password: " + password);

        Collection<User> allUsers = userRepository.findAll();
        Predicate<User> streamsUser = item -> item.getEmail().equals(email);
        List<User> results = allUsers.stream().filter(streamsUser).collect(Collectors.toList());

        if (results.size() == 1) {
            User user = results.get(0);
            if (user.getPassword().equals(password)) {
                return new ResponseEntity<>("Success", headers, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Invalid Credentials", headers, HttpStatus.UNAUTHORIZED);

    }

}
