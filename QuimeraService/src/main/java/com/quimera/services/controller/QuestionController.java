package com.quimera.services.controller;

import com.quimera.services.model.Question;
import com.quimera.services.repositories.QuestionRepository;
import com.quimera.services.util.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Manu on 5/2/16.
 */
@RestController
@RequestMapping("/questions")
public class QuestionController {

    @PostConstruct
    public void init(){
        questionRepository.save(DataGenerator.questionsExamples());
    }

    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping("/insertOrUpdate")
    public void insert(@RequestBody Question question) {
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
    public void delete(@RequestBody Question question) {
        questionRepository.delete(question);
    }


}
