package com.olivercormier.sidebracketapi.results;

import lombok.Data;

@Data
public class Result {
    public int ID;
    public int tournamentID, participantID;
    public int placing;
}
