package com.olivercormier.sidebracketapi.results;

import lombok.Data;

@Data
public class Result {
    public int id;
    public int tournamentId;
    public String participantName;
    public int placing;

    public Result(int tournamentId, String participantName, int placing) {
        this.tournamentId = tournamentId;
        this.participantName = participantName;
        this.placing = placing;
    }
}
