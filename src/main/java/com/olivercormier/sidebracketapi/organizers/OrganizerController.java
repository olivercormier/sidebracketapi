package com.olivercormier.sidebracketapi.organizers;

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
public class OrganizerController {

    private OrganizerDao organizers;

    public OrganizerController() {
        this.organizers = new OrganizerDao();
    }

    @GetMapping("/organizers/{id}")
    ResponseEntity<Object> read(@PathVariable("id") String id) {
        Optional<Organizer> result = organizers.get(Integer.parseInt(id));
        if (result.isPresent()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Organizer Record Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/organizers")
    ResponseEntity<Object> all() {
        return new ResponseEntity<>(organizers.getAll(), HttpStatus.OK);
    }

    @PostMapping("/organizers")
    ResponseEntity<Object> create(@RequestBody Organizer organizer) {
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(organizers.save(organizer)).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/organizers/{id}")
    ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Organizer organizer) {
        organizer.setId(Integer.parseInt(id));
        organizers.update(organizer);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/organizers/{id}")
    ResponseEntity<Object> delete(@PathVariable("id") String id) {
        organizers.delete(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }
}
