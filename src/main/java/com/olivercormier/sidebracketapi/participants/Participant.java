package com.olivercormier.sidebracketapi.participants;

import lombok.Data;

@Data
public class Participant {
    public int id;
    public int userId;

    //Create and Update won't work without this (reason unknown)
    public Participant() {}

    public Participant(int userId) {
        this.userId = userId;
    }
}
