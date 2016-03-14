package com.quimera.controller;

import com.quimera.model.Question;
import com.quimera.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Manu on 5/2/16.
 */
@CrossOrigin
@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insert(@RequestBody Question question) {
        questionService.insert(question);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody Question question) {
        questionService.update(question);
    }

    @RequestMapping("/getAll")
    public List<Question> getAll() {
        return questionService.findAll();
    }

    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    public Question get(@RequestBody String id) {
        return questionService.find(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody Question question) {
        questionService.delete(question);
    }


}
