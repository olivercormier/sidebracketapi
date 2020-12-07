package com.olivercormier.sidebracketapi.organizers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class OrganizerController {

    private OrganizerDao organizers;

    public OrganizerController() {
        this.organizers = new OrganizerDao();
    }

    @GetMapping("/organizers")
    Collection<Organizer> all() {
        return organizers.getAll();
    }
}
