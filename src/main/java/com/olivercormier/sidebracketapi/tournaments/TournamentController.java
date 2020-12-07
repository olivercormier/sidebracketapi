package com.olivercormier.sidebracketapi.tournaments;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TournamentController {

    private TournamentDao tournaments;

    public TournamentController() {
        this.tournaments = new TournamentDao();
    }

    @GetMapping("/tournaments")
    Collection<Tournament> all() {
        return tournaments.getAll();
    }
}
