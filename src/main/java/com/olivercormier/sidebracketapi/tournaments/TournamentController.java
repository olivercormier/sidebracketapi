package com.olivercormier.sidebracketapi.tournaments;

import com.olivercormier.sidebracketapi.users.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class TournamentController {

    private TournamentDao tournaments;

    public TournamentController() {
        this.tournaments = new TournamentDao();
    }

    @GetMapping("/tournaments/{id}")
    ResponseEntity<Object> read(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");
        Optional<Tournament> result = tournaments.get(Integer.parseInt(id));
        if (result.isPresent()) {
            return new ResponseEntity<>(result, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Tournament Record Not Found", headers, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tournaments")
    ResponseEntity<Object> all() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");
        return new ResponseEntity<>(tournaments.getAll(), headers, HttpStatus.OK);
    }

    @PostMapping("/tournaments")
    ResponseEntity<Object> create(@RequestBody Tournament tournament) {
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(tournaments.save(tournament)).toUri();
        headers.setLocation(location);
        headers.setAccessControlAllowOrigin("*");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/tournaments/{id}")
    ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Tournament tournament) {
        tournament.setId(Integer.parseInt(id));
        tournaments.update(tournament);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tournaments/{id}")
    ResponseEntity<Object> delete(@PathVariable("id") String id) {
        tournaments.delete(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }
}
