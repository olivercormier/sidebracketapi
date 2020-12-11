package com.olivercormier.sidebracketapi.games;

import com.olivercormier.sidebracketapi.users.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
public class GameController {

    private GameDao games;

    public GameController() {
        this.games = new GameDao();
    }

    @GetMapping("/games/{id}")
    ResponseEntity<Object> read(@PathVariable("id") String id) {
        Optional<Game> result = games.get(Integer.parseInt(id));
        if (result.isPresent()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Game Record Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/games")
    ResponseEntity<Object> all() {
        return new ResponseEntity<>(games.getAll(), HttpStatus.OK);
    }

    @PostMapping("/games")
    ResponseEntity<Object> create(@RequestBody Game game) {
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(games.save(game)).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/games/{id}")
    ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Game game) {
        game.setId(Integer.parseInt(id));
        games.update(game);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/games/{id}")
    ResponseEntity<Object> delete(@PathVariable("id") String id) {
        games.delete(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

}
