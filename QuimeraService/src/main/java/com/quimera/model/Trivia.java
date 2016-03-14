package com.quimera.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manu on 31/1/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trivia {

    @Id
    private String idTrivia;
    private int rounds;
    private String name;

    private List<Question> questions;

    public List<Question> getQuestions() {
        if (questions == null) {
            questions = new ArrayList<>();
        }
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdTrivia() {
        return idTrivia;
    }

    public void setIdTrivia(String idTrivia) {
        this.idTrivia = idTrivia;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

}
