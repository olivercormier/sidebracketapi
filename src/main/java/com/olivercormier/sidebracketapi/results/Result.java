package com.olivercormier.sidebracketapi.results;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Result {

    @Id
    public String id;

    public String tournamentId;
    public String participantName;
    public int placing;

    public Result(String tournamentId, String participantName, int placing) {
        this.tournamentId = tournamentId;
        this.participantName = participantName;
        this.placing = placing;
    }
}
