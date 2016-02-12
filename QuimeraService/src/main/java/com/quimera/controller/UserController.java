package com.quimera.controller;

import com.quimera.model.User;
import com.quimera.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Manu on 6/2/16.
 */
@RestController
@RequestMapping("/participant")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/insertOrUpdate")
    public void insert(@RequestBody User user) {
        userService.insert(user);
    }

    @RequestMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @RequestMapping("/find")
    public User find(String id) {
        return userService.find(id);
    }

    @RequestMapping("/delete")
    public void delete(@RequestBody User user) {
        userService.delete(user);
    }

}
