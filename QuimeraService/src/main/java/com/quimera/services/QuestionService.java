package com.quimera.services;

import com.quimera.model.Question;
import com.quimera.repositories.QuestionRepository;
import com.quimera.util.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Manu on 12/2/16.
 */
@Component
public class QuestionService {

    @PostConstruct
    public void init() {
        questionRepository.save(DataGenerator.questionsExamples());
    }

    @Autowired
    private QuestionRepository questionRepository;

    public void insert(Question question) {
        questionRepository.save(question);
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question find(String id) {
        return questionRepository.findOne(id);
    }

    public void delete(Question question) {
        questionRepository.delete(question);
    }

}
