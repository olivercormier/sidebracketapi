package com.olivercormier.sidebracketapi.participants;

import lombok.Data;

@Data
public class Participant {
    public int id;
    public int userId;

    public Participant(int userId) {
        this.userId = userId;
    }
}
