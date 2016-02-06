package com.quimera.services.controller;

import com.quimera.services.model.Bar;
import com.quimera.services.model.Participant;
import com.quimera.services.repositories.BarRepository;
import com.quimera.services.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Manu on 6/2/16.
 */
@RestController
@RequestMapping("/bar")
public class BarController {

    @Autowired
    BarRepository barRepository;

    @RequestMapping(value = "/insertOrUpdate")
    public void insert(Bar bar) {
        barRepository.save(bar);
    }

    @RequestMapping(value = "/findAll")
    public List<Bar> findAll() {
        return barRepository.findAll();
    }

    @RequestMapping(value = "/find")
    public Bar find(String id) {
        return barRepository.findOne(id);
    }

    @RequestMapping(value = "/delete")
    public void delete(Bar bar) {
        barRepository.delete(bar);
    }

}
