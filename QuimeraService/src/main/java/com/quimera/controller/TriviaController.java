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

    @RequestMapping("start")
    public Message startTrivia() {
        return triviaService.startTrivia();
    }

    @RequestMapping("stop")
    public Message stopTrivia() {
        return triviaService.stopTrivia();
    }

//    @RequestMapping("pause")
//    public boolean pauseTrivia() {
//        return triviaService.pauseTrivia();
//    }
//
//    @RequestMapping("resume")
//    public boolean resumeTrivia() {
//        return triviaService.resumeTrivia();
//    }

    @RequestMapping("getStatus")
    public Message statusTrivia() {
        return triviaService.statusTrivia();
    }

    @ResponseStatus(value = HttpStatus.OK, reason = "Trivia asignada satisfactoriamente.")  // 200
    @RequestMapping(value = "/setCurrentTrivia", method = RequestMethod.POST)
    public void setCurrentTrivia(@RequestBody String idTrivia) {
        triviaService.setCurrentTrivia(idTrivia);
    }

    @RequestMapping(value = "/getCurrentTrivia", method = RequestMethod.GET)
    public Trivia getCurrentTrivia() {
        return triviaService.getCurrentTrivia();
    }

    @RequestMapping(value = "/getCurrentBanners", method = RequestMethod.GET)
    public List<Banner> getCurrentBanners() {
        return triviaService.getCurrentBanners();
    }

    @RequestMapping(value = "/getCurrentQuestionPosition", method = RequestMethod.GET)
    public int getCurrentQuestionPosition() {
        return triviaService.getCurrentQuestionPosition();
    }

    @RequestMapping(value = "/elapsedTime", method = RequestMethod.GET)
    public int getElapsedTime() {
        return triviaService.getElapsedTime();
    }


}