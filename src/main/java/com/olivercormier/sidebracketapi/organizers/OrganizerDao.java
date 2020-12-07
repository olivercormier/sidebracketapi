package com.olivercormier.sidebracketapi.organizers;

import com.olivercormier.sidebracketapi.Dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class OrganizerDao implements Dao<Organizer> {

    private HashMap<Integer, Organizer> organizerHashMap;
    private int idCounter;

    public OrganizerDao() {
        idCounter = 0;
        organizerHashMap = new HashMap<Integer, Organizer>();
        add(1);
        add(2);
        add(3);
    }

    private void add(int userId) {
        Organizer organizer = new Organizer(userId);
        this.save(organizer);
    }

    @Override
    public Optional<Organizer> get(int id) {
        Optional<Organizer> response;
        Organizer result = organizerHashMap.get(id);
        if (result == null) {
            response = Optional.empty();
        } else {
            response = Optional.of(result);
        }
        return response;
    }

    @Override
    public Collection<Organizer> getAll() {
        return organizerHashMap.values();
    }

    @Override
    public int save(Organizer organizer) {
        idCounter += 1;
        organizer.setId(idCounter);
        organizerHashMap.put(idCounter, organizer);
        return idCounter;
    }

    @Override
    public void update(Organizer organizer) {
        int id = organizer.getId();
        organizerHashMap.put(id, organizer);
    }

    @Override
    public void delete(Organizer organizer) {
        int id = organizer.getId();
        organizerHashMap.remove(id, organizer);
    }
}
