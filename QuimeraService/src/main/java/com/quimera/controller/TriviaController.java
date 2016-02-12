package com.quimera.controller;

import com.quimera.model.Answer;
import com.quimera.model.Bar;
import com.quimera.model.Question;
import com.quimera.model.Score;
import com.quimera.services.TriviaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Manu on 31/1/16.
 */
@RestController
@RequestMapping("/trivia")
public class TriviaController {

    @Autowired
    private TriviaService triviaService;

    @RequestMapping("getQuestion")
    public Question getQuestion() {
        return triviaService.getQuestion();
    }

    @RequestMapping("pushAnswer")
    public Answer pushAnswer(@RequestBody Answer answer) {
        return triviaService.pushAnswer(answer);
    }

    @RequestMapping("getScore")
    public List<Score> getScore(@RequestBody Bar bar) {
       return triviaService.getScore(bar);
    }


}