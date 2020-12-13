package com.olivercormier.sidebracketapi.results;

import com.olivercormier.sidebracketapi.Dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class ResultDao implements Dao<Result> {

    private HashMap<Integer, Result> resultHashMap;
    private int idCounter;

    public ResultDao() {
        idCounter = 0;
        resultHashMap = new HashMap<Integer, Result>();
        add(1, "Oliver", 1);
        add(1, "Tim", 2);
        add(2, "Joe", 1);
    }

    private void add(int tournamentId, String participantName, int placing) {
        Result result = new Result(tournamentId, participantName, placing);
        this.save(result);
    }

    @Override
    public Optional<Result> get(int id) {
        Optional<Result> response;
        Result resultId = resultHashMap.get(id);
        if (resultId == null) {
            response = Optional.empty();
        } else {
            response = Optional.of(resultId);
        }
        return response;
    }

    @Override
    public Collection<Result> getAll() {
        return resultHashMap.values();
    }

    @Override
    public int save(Result result) {
        idCounter += 1;
        result.setId(idCounter);
        resultHashMap.put(idCounter, result);
        return idCounter;
    }

    @Override
    public void update(Result result) {
        int id = result.getId();
        resultHashMap.put(id, result);
    }

    @Override
    public void delete(int id) {
        resultHashMap.remove(id);
    }
}
