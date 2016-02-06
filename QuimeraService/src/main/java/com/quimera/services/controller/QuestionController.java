package com.quimera.services.controller;

import com.quimera.services.model.Question;
import com.quimera.services.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private QuestionRepository questionRepository;

    @RequestMapping("/insertOrUpdate")
    public void insert(Question question) {
        questionRepository.save(question);
    }

    @RequestMapping("/findAll")
    public List<Question> findAll() {
        return questionRepository.findAll();

    }

    @RequestMapping("/find")
    public Question find(String id) {
        return questionRepository.findOne(id);
    }

    @RequestMapping("/delete")
    public void delete(Question question) {
        questionRepository.delete(question);
    }


}
