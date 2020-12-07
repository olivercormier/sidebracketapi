package com.olivercormier.sidebracketapi.results;

import lombok.Data;

@Data
public class Result {
    public int id;
    public int tournamentId, participantId;
    public int placing;

    public Result(int tournamentId, int participantId, int placing) {
        this.tournamentId = tournamentId;
        this.participantId = participantId;
        this.placing = placing;
    }
}
