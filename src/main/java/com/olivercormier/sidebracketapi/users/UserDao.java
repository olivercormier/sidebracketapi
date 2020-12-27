package com.olivercormier.sidebracketapi.users;

import com.olivercormier.sidebracketapi.Dao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {

    private HashMap<Integer,User> userHashMap;
    private int idCounter;

    @Autowired
    private UserRepository userRepository;
    private List<User> userList;
    private User user1;

    public UserDao() {
        /*idCounter = 0;
        userHashMap = new HashMap<Integer, User>();
        add("Oliver", "Password", "Oliver@email.com");
        add("Tim", "Pass", "Tim@email.com");
        add("Joe", "Test", "Joe@email.com"); */
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
        //return userHashMap.values();
        userList = userRepository.findAll();
        if (userList.isEmpty()) {
            return null;
        } else {
            return userList;
        }
    }

    @Override
    public User save(User user) {
        idCounter += 1;
        //user.setId(idCounter);
        user1 = userRepository.save(user);
        //userHashMap.put(idCounter, user);
        return user1;
    }

    @Override
    public void update(User user) {
        //int id = user.getId();
        //userHashMap.put(id, user);
    }

    @Override
    public void delete(int id) {
        userHashMap.remove(id);
    }

}
