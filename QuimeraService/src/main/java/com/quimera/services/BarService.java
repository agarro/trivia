package com.quimera.services;

import com.quimera.model.Bar;
import com.quimera.repositories.BarRepository;
import com.quimera.util.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Manu on 11/2/16.
 */
@Component
public class BarService {

    @PostConstruct
    public void init() {
        barRepository.save(DataGenerator.barsExamples());
    }

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private BarRepository barRepository;

    public void insert(Bar bar) {
        barRepository.save(bar);
    }

    public List<Bar> findAll() {
        return barRepository.findAll();
    }

    public Bar find(String id) {
        return barRepository.findOne(id);
    }

    public void delete(Bar bar) {
        barRepository.delete(bar);
    }
}
