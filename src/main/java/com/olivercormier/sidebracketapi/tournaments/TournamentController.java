package com.olivercormier.sidebracketapi.tournaments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class TournamentController {

    @Autowired
    public TournamentRepository tournamentRepository;

    public TournamentController() {}

    @GetMapping("/tournaments/{id}")
    ResponseEntity<Object> read(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");
        Optional<Tournament> result = tournamentRepository.findById(id);
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
        return new ResponseEntity<>(tournamentRepository.findAll(), headers, HttpStatus.OK);
    }

    @PostMapping("/tournaments")
    ResponseEntity<Object> create(@RequestParam(name = "tournamentName") String tournamentName,
                                  @RequestParam(name = "gameName") String gameName) {
        Tournament tournament = new Tournament(tournamentName, 1, 1);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(tournamentRepository.save(tournament)).toUri();
        headers.setLocation(location);
        headers.setAccessControlAllowOrigin("*");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/tournaments/{id}")
    ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Tournament tournament) {
        //tournament.setId(Integer.parseInt(id));
        //tournaments.update(tournament);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tournaments/{id}")
    ResponseEntity<Object> delete(@PathVariable("id") String id) {
        //tournaments.delete(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }
}
