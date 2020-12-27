package com.olivercormier.sidebracketapi.games;

import com.olivercormier.sidebracketapi.Dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class GameDao implements Dao<Game> {

    private HashMap<Integer, Game> gameHashMap;
    private int idCounter;

    public GameDao() {
        idCounter = 0;
        gameHashMap = new HashMap<Integer, Game>();
        add("Samurai Shodown");
        add("Street Fighter V");
        add("Super Smash Bros. Ultimate");
    }

    private void add(String name) {
        Game game = new Game(name);
        this.save(game);
    }

    @Override
    public Optional<Game> get(int id) {
        Optional<Game> response;
        Game result = gameHashMap.get(id);
        if (result == null) {
            response = Optional.empty();
        } else {
            response = Optional.of(result);
        }
        return response;
    }

    @Override
    public Collection<Game> getAll() {
        return gameHashMap.values();
    }

    @Override
    public Game save(Game game) {
        idCounter += 1;
        game.setId(idCounter);
        gameHashMap.put(idCounter, game);
        return game;
    }

    @Override
    public void update(Game game) {
        int id = game.getId();
        gameHashMap.put(id, game);
    }

    @Override
    public void delete(int id) {
        gameHashMap.remove(id);
    }
}
