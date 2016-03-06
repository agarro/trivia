package com.quimera.controller;

import com.quimera.model.User;
import com.quimera.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Manu on 6/2/16.
 */
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/insertOrUpdate")
    public void insert(@RequestBody User user) {
        userService.insert(user);
    }

    @RequestMapping("/getAll")
    public List<User> getAll() {
        return userService.findAll();
    }

    @RequestMapping("/getById")
    public User get(String id) {
        return userService.find(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody User user) {
        userService.delete(user);
    }

    @RequestMapping(value = "/getByUsername", method = RequestMethod.POST)
    public User getByUsername(@RequestBody String username) {
        return userService.getByUsername(username);
    }

    @RequestMapping("/deleteAll")
    public void deleteAll() {
        userService.deleteAll();
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public User authenticate(@RequestBody User user) {
        return userService.authenticate(user.getUsername(), user.getPassword());
    }

}
