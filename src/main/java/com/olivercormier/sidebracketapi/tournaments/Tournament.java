package com.olivercormier.sidebracketapi.tournaments;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class Tournament {
    public int ID;
    public String name;
    public int organizerID, gameID;
    public Date date;
    public Time time;
}
