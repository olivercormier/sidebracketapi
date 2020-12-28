/*package com.olivercormier.sidebracketapi.tournaments;

import com.olivercormier.sidebracketapi.Dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class TournamentDao implements Dao<Tournament> {

    private HashMap<Integer, Tournament> tournamentHashMap;
    private int idCounter;

    public TournamentDao() {
        idCounter = 0;
        tournamentHashMap = new HashMap<Integer, Tournament>();
        add("Samsho Weekly #10", 1, 1);
        add("SFV Weekly #1", 1, 2);
        add("Smash Weekly #100", 2, 3);
    }

    private void add(String name, int organizerId, int gameId) {
        Tournament tournament = new Tournament(name, organizerId, gameId);
        this.save(tournament);
    }

    @Override
    public Optional<Tournament> get(int id) {
        Optional<Tournament> response;
        Tournament result = tournamentHashMap.get(id);
        if (result == null) {
            response = Optional.empty();
        } else {
            response = Optional.of(result);
        }
        return response;
    }

    @Override
    public Collection<Tournament> getAll() {
        return tournamentHashMap.values();
    }

    @Override
    public Tournament save(Tournament tournament) {
        idCounter += 1;
        //tournament.setId(idCounter);
        tournamentHashMap.put(idCounter, tournament);
        return tournament;
    }

    @Override
    public void update(Tournament tournament) {
        //int id = tournament.getId();
        //tournamentHashMap.put(id, tournament);
    }

    @Override
    public void delete(int id) {
        tournamentHashMap.remove(id);
    }
}*/
