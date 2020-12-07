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
        add(1);
        add(2);
        add(3);
    }

    private void add(int userId) {
        Participant participant = new Participant(userId);
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
    public int save(Participant participant) {
        idCounter += 1;
        participant.setId(idCounter);
        participantHashMap.put(idCounter, participant);
        return idCounter;
    }

    @Override
    public void update(Participant participant) {
        int id = participant.getId();
        participantHashMap.put(id, participant);
    }

    @Override
    public void delete(Participant participant) {
        int id = participant.getId();
        participantHashMap.remove(id, participant);
    }
}
