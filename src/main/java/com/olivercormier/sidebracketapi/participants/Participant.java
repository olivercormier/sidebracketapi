package com.olivercormier.sidebracketapi.participants;

import lombok.Data;

@Data
public class Participant {
    public int id;
    public int userId;
    public String name;

    //Create and Update won't work without this (reason unknown)
    public Participant() {}

    public Participant(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
