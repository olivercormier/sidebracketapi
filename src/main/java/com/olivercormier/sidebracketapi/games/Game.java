package com.olivercormier.sidebracketapi.games;

import lombok.Data;

@Data
public class Game {
    public int id;
    public String name;

    //Create and Update won't work without this (reason unknown)
    public Game() {}

    public Game(String name) {
        this.name = name;
    }
}
