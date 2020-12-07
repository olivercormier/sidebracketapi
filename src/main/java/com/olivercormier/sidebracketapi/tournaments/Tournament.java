package com.olivercormier.sidebracketapi.tournaments;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class Tournament {
    public int id;
    public String name;
    public int organizerId, gameId;
    public Date date;
    public Time time;

    public Tournament(String name, int organizerId, int gameId) {
        this.name = name;
        this.organizerId = organizerId;
        this.gameId = gameId;
    }
}
