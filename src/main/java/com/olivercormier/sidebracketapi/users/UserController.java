package com.olivercormier.sidebracketapi.users;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
public class UserController {

    private UserDao users;

    public UserController() {
        this.users = new UserDao();
    }

    @GetMapping("/users/{id}")
    ResponseEntity<Object> read(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");
        Optional<User> result = users.get(Integer.parseInt(id));
        if (result.isPresent()) {
            return new ResponseEntity<>(result, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User Record Not Found", headers, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users")
    ResponseEntity<Object> all() {
        return new ResponseEntity<>(users.getAll(), HttpStatus.OK);
    }

    @PostMapping("/users")
    ResponseEntity<Object> create(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(users.save(user)).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody User user) {
        user.setId(Integer.parseInt(id));
        users.update(user);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<Object> delete(@PathVariable("id") String id) {
        users.delete(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

}
