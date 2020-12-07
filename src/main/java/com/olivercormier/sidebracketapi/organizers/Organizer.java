package com.olivercormier.sidebracketapi.organizers;

import lombok.Data;

@Data
public class Organizer {
    public int id;
    public int userId;

    public Organizer(int userId) {
        this.userId = userId;
    }
}
