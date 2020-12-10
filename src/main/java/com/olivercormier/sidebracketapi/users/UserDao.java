package com.olivercormier.sidebracketapi.users;

import com.olivercormier.sidebracketapi.Dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class UserDao implements Dao<User> {

    private HashMap<Integer,User> userHashMap;
    private int idCounter;

    public UserDao() {
        idCounter = 0;
        userHashMap = new HashMap<Integer, User>();
        add("Oliver", "Password", "Oliver@email.com");
        add("Tim", "Pass", "Tim@email.com");
        add("Joe", "Test", "Joe@email.com");
    }

    private void add(String username, String password, String email) {
        User user = new User(username, password, email);
        this.save(user);
    }

    @Override
    public Optional<User> get(int id) {
        Optional<User> response;
        User result = userHashMap.get(id);
        if (result == null) {
            response = Optional.empty();
        } else {
            response = Optional.of(result);
        }
        return response;
    }

    @Override
    public Collection<User> getAll() {
        return userHashMap.values();
    }

    @Override
    public int save(User user) {
        idCounter += 1;
        user.setId(idCounter);
        userHashMap.put(idCounter, user);
        return idCounter;
    }

    @Override
    public void update(User user) {
        int id = user.getId();
        userHashMap.put(id, user);
    }

    @Override
    public void delete(int id) {
        userHashMap.remove(id);
    }

}
