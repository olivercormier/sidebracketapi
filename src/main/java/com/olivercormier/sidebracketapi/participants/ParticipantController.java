package com.olivercormier.sidebracketapi.participants;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ParticipantController {

    private ParticipantDao participants;

    public ParticipantController() {
        this.participants = new ParticipantDao();
    }

    @GetMapping("/participants")
    Collection<Participant> all() {
        return participants.getAll();
    }
}
