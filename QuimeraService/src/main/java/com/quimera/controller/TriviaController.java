package com.quimera.controller;

import com.quimera.model.*;
import com.quimera.services.TriviaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Manu on 31/1/16.
 */
@RestController
@CrossOrigin
@RequestMapping("/trivia")
public class TriviaController {

    @Autowired
    private TriviaService triviaService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insert(@RequestBody Trivia trivia) {
        triviaService.insert(trivia);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody Trivia trivia) {
        trivia.setTriviaStatus(TriviaStatus.NEW);
        triviaService.update(trivia);
    }

    @RequestMapping("/getAll")
    public List<Trivia> getAll() {
        return triviaService.findAll();
    }

    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    public Trivia get(@RequestBody String id) {
        return triviaService.find(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody Trivia trivia) {
        triviaService.delete(trivia);
    }

    @RequestMapping("getCurrentQuestion")
    public Question getCurrentQuestion() {
        return triviaService.getCurrentQuestion();
    }

    @RequestMapping(value = "/pushAnswer", method = RequestMethod.POST)
    public Answer pushAnswer(@RequestBody Answer answer) {
        return triviaService.pushAnswer(answer);
    }

    @RequestMapping(value = "/getScore", method = RequestMethod.POST)
    public List<Score> getScore(@RequestBody Bar bar) {
        return triviaService.getScore(bar);
    }


}