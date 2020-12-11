package com.olivercormier.sidebracketapi.participants;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class ParticipantController {

    private ParticipantDao participants;

    public ParticipantController() {
        this.participants = new ParticipantDao();
    }

    @GetMapping("/participants/{id}")
    ResponseEntity<Object> read(@PathVariable("id") String id) {
        Optional<Participant> result = participants.get(Integer.parseInt(id));
        if (result.isPresent()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Participant Record Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/participants")
    ResponseEntity<Object> all() {
        return new ResponseEntity<>(participants.getAll(), HttpStatus.OK);
    }

    @PostMapping("/participants")
    ResponseEntity<Object> create(@RequestBody Participant participant) {
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(participants.save(participant)).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/participants/{id}")
    ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Participant participant) {
        participant.setId(Integer.parseInt(id));
        participants.update(participant);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/participants/{id}")
    ResponseEntity<Object> delete(@PathVariable("id") String id) {
        participants.delete(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }
}
