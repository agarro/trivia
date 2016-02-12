package com.quimera.controller;

import com.quimera.model.Question;
import com.quimera.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Manu on 5/2/16.
 */
@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/insertOrUpdate")
    public void insert(@RequestBody Question question) {
        questionService.insert(question);
    }

    @RequestMapping("/findAll")
    public List<Question> findAll() {
        return questionService.findAll();
    }

    @RequestMapping("/find")
    public Question find(String id) {
        return questionService.find(id);
    }

    @RequestMapping("/delete")
    public void delete(@RequestBody Question question) {
        questionService.delete(question);
    }


}
