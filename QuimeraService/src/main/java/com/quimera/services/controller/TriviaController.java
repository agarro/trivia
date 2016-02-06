package com.quimera.services.controller;

import com.quimera.services.Util.DataGenerator;
import com.quimera.services.Util.QuestionGenerator;
import com.quimera.services.model.*;
import com.quimera.services.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by Manu on 31/1/16.
 */
@RestController
@RequestMapping("/trivia")
public class TriviaController {

    private static List<Question> questionsList;
    public static Trivia trivia;
    public static Question activeQuestion;
    private static Set<Participant> participantSet = new HashSet<>();
    private static Map<String, BarController> barList = new HashMap<>();
    private static Set<Answer> answerSet = new HashSet<>();

    @Autowired
    private QuestionRepository questionRepository;

    @PostConstruct
    public void init() {
        questionRepository.save(DataGenerator.questionsExample());
        trivia = generateTrivia();
        new Thread(new QuestionGenerator()).start();

    }

    @RequestMapping(value = "getQuestion", method = RequestMethod.GET)
    public Question getQuestion() {

        return activeQuestion;
    }

    @RequestMapping(value = "pushAnswer", method = RequestMethod.GET)
    public Question pushAnswer(Answer answer){

        participantSet.add(answer.getParticipant());
        if (!answerSet.contains(answer) && answer.getQuestion().getIdQuestion().equals(activeQuestion.getIdQuestion())) {
            answerSet.add(answer);
        }
        return activeQuestion;
    }

    @RequestMapping(value = "getPoints", method = RequestMethod.GET)
    public List<Point> getPoints() {

        HashMap<String, Point> points = new HashMap<>();

        for (Answer answer : answerSet) {
            String idParticipant = answer.getParticipant().getIdParticipant();
            Point point;
            if (points.containsKey(idParticipant)) {
                point = points.get(idParticipant);
            } else {
                point = new Point();
                point.setParticipant(answer.getParticipant());
            }
            point.setPoints(point.getPoints() + (answer.isCorrectAnswer() ? 5 : 0));
            points.put(idParticipant, point);
        }

        return new ArrayList<>(points.values());
    }

    private Trivia generateTrivia() {
        Trivia trivia = new Trivia();

        questionsList = questionRepository.findAll();
        Collections.shuffle(questionsList);
        trivia.getQuestionList().addAll(questionsList.subList(0, 5));

        return trivia;

    }

}