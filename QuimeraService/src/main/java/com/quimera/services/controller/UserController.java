package com.quimera.services.controller;

import com.quimera.services.model.User;
import com.quimera.services.repositories.ParticipantRepository;
import com.quimera.services.util.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Manu on 6/2/16.
 */
@RestController
@RequestMapping("/participant")
public class UserController {

    @PostConstruct
    public void init(){
        participantRepository.save(DataGenerator.usersExamples());
    }

    @Autowired
    private ParticipantRepository participantRepository;

    @RequestMapping("/insertOrUpdate")
    public void insert(@RequestBody User user) {
        participantRepository.save(user);
    }

    @RequestMapping("/findAll")
    public List<User> findAll() {
        return participantRepository.findAll();
    }

    @RequestMapping("/find")
    public User find(String id) {
        return participantRepository.findOne(id);
    }

    @RequestMapping("/delete")
    public void delete(@RequestBody User user) {
        participantRepository.delete(user);

    }

}
