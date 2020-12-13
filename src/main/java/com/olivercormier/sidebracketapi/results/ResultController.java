package com.olivercormier.sidebracketapi.results;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class ResultController {

    private ResultDao results;

    public ResultController() {
        this.results = new ResultDao();
    }

    @GetMapping("/results/{id}")
    ResponseEntity<Object> read(@PathVariable("id") String id) {
        Optional<Result> result = results.get(Integer.parseInt(id));
        if (result.isPresent()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Result Record Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/results")
    ResponseEntity<Object> all(@RequestParam(name = "tournamentId", required = false) String tournamentId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");
        Collection<Result> allResults = results.getAll();
        if (tournamentId != null && !tournamentId.trim().isEmpty()) {
            Predicate<Result> streamsResult = item -> item.getTournamentId() == Integer.parseInt(tournamentId);
            return new ResponseEntity<>(allResults.stream().filter(streamsResult).collect(Collectors.toList()),
                    headers, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(allResults, headers, HttpStatus.OK);
        }
    }

    @PostMapping("/results")
    ResponseEntity<Object> create(@RequestBody Result result) {
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(results.save(result)).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/results/{id}")
    ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Result result) {
        result.setId(Integer.parseInt(id));
        results.update(result);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/results/{id}")
    ResponseEntity<Object> delete(@PathVariable("id") String id) {
        results.delete(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }
}
