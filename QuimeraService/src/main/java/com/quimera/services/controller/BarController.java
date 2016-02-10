package com.quimera.services.controller;

import com.quimera.services.util.DataGenerator;
import com.quimera.services.model.Bar;
import com.quimera.services.repositories.BarRepository;
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
@RequestMapping("/bar")
public class BarController {

    @PostConstruct
    public void init(){
        barRepository.save(DataGenerator.barsExamples());
    }

    @Autowired
    private BarRepository barRepository;

    @RequestMapping("/insertOrUpdate")
    public void insert(@RequestBody Bar bar) {
        barRepository.save(bar);
    }

    @RequestMapping("/findAll")
    public List<Bar> findAll() {
        return barRepository.findAll();
    }

    @RequestMapping("/find")
    public Bar find(String id) {
        return barRepository.findOne(id);
    }

    @RequestMapping("/delete")
    public void delete(@RequestBody Bar bar) {
        barRepository.delete(bar);
    }

}
