package com.olivercormier.sidebracketapi.participants;

import com.olivercormier.sidebracketapi.Dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class ParticipantDao implements Dao<Participant> {

    private HashMap<Integer, Participant> participantHashMap;
    private int idCounter;

    public ParticipantDao() {
        idCounter = 0;
        participantHashMap = new HashMap<Integer, Participant>();
        add(1, "Oliver");
        add(2, "Tim");
        add(3, "Joe");
    }

    private void add(int userId, String name) {
        Participant participant = new Participant(userId, name);
        this.save(participant);
    }

    @Override
    public Optional<Participant> get(int id) {
        Optional<Participant> response;
        Participant result = participantHashMap.get(id);
        if (result == null) {
            response = Optional.empty();
        } else {
            response = Optional.of(result);
        }
        return response;
    }

    @Override
    public Collection<Participant> getAll() {
        return participantHashMap.values();
    }

    @Override
    public Participant save(Participant participant) {
        idCounter += 1;
        participant.setId(idCounter);
        participantHashMap.put(idCounter, participant);
        return participant;
    }

    @Override
    public void update(Participant participant) {
        int id = participant.getId();
        participantHashMap.put(id, participant);
    }

    @Override
    public void delete(int id) {
        participantHashMap.remove(id);
    }
}
