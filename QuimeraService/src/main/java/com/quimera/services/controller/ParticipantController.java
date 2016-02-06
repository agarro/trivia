package com.quimera.services.controller;

import com.quimera.services.model.Participant;
import com.quimera.services.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Manu on 6/2/16.
 */
@RestController
@RequestMapping("/participant")
public class ParticipantController {

    @Autowired
    ParticipantRepository participantRepository;

    @RequestMapping(value = "/insertOrUpdate")
    public void insert(Participant participant) {
        participantRepository.save(participant);
    }

    @RequestMapping(value = "/findAll")
    public List<Participant> findAll() {
        return participantRepository.findAll();
    }

    @RequestMapping(value = "/find")
    public Participant find(String id) {
        return participantRepository.findOne(id);
    }

    @RequestMapping(value = "/delete")
    public void delete(Participant participant) {
        participantRepository.delete(participant);

    }

}
