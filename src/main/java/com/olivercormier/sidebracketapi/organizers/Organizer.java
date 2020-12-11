package com.olivercormier.sidebracketapi.organizers;

import lombok.Data;

@Data
public class Organizer {
    public int id;
    public int userId;

    //Create and Update won't work without this (reason unknown)
    public Organizer() {}

    public Organizer(int userId) {
        this.userId = userId;
    }
}
