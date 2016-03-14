package com.quimera.services;

import com.quimera.model.User;
import com.quimera.repositories.UserRepository;
import com.quimera.util.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Manu on 12/2/16.
 */
@Component
public class UserService {

    @Autowired
    private UserRepository userService;

    @PostConstruct
    public void init() {
        userService.save(DataGenerator.usersExamples());
    }

    public void insert(User user) {
        userService.insert(user);
    }

    public void update(User user) {
        userService.save(user);
    }

    public List<User> findAll() {
        return userService.findAll();
    }

    public User find(String id) {
        return userService.findOne(id);
    }

    public void delete(User user) {
        userService.delete(user);
    }

    public void deleteAll(){
        userService.deleteAll();
    }

    public User getByUsername(String username){
        return userService.findByUsername(username);
    }

    public User authenticate(String username, String password) {

        return userService.findByUsernameAndPassword(username, password);

    }
}
