package com.olivercormier.sidebracketapi.games;

import lombok.Data;

@Data
public class Game {
    public int id;
    public String name;

    public Game(String name) {
        this.name = name;
    }
}
