package com.olivercormier.sidebracketapi.games;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class GameController {

    private GameDao games;

    public GameController() {
        this.games = new GameDao();
    }

    @GetMapping("/games")
    Collection<Game> all() {
        return games.getAll();
    }
}
