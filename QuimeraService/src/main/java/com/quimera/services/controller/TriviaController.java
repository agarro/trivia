package com.quimera.services.controller;

import com.quimera.services.repositories.UserRepository;
import com.quimera.services.util.DataGenerator;
import com.quimera.services.util.QuestionGenerator;
import com.quimera.services.model.*;
import com.quimera.services.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private static Set<Answer> answerSet = new HashSet<>();

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        trivia = generateTrivia();
        new Thread(new QuestionGenerator()).start();

    }

    @RequestMapping("getQuestion")
    public Question getQuestion() {

        return activeQuestion;
    }

    @RequestMapping("pushAnswer")
    public Answer pushAnswer(@RequestBody Answer answer) {

        User user = userRepository.findOne(answer.getQuestion().getIdQuestion()); //User validation
        if (user!= null && !answerSet.contains(answer) && answer.getQuestion().getIdQuestion().equals(activeQuestion.getIdQuestion())) {
            answerSet.add(answer);
        }
        return answer;
    }

    @RequestMapping("getScore")
    public List<Score> getScore(@RequestBody Bar bar) {

        HashMap<String, Score> scoreHashMap = new HashMap<>();

        for (Answer answer : answerSet) {
            if (answer.getBar().equals(bar)) {
                String idParticipant = answer.getUser().getIdUser();
                Score score;
                if (scoreHashMap.containsKey(idParticipant)) {
                    score = scoreHashMap.get(idParticipant);
                } else {
                    score = new Score();
                    score.setUser(answer.getUser());
                }
                score.setScore(score.getScore() + (answer.isCorrectAnswer() ? Constant.POINTS_CORRECT_ANSWER : Constant.POINTS_WRONG_ANSWER));
                scoreHashMap.put(idParticipant, score);
            }
        }
        List<Score> scoresList = new ArrayList<>(scoreHashMap.values());
        Collections.sort(scoresList);

        return scoresList;
    }

    private Trivia generateTrivia() {

        Trivia trivia = new Trivia();

        questionsList = questionRepository.findAll();
        Collections.shuffle(questionsList);
        trivia.getQuestionList().addAll(questionsList.subList(0, Constant.QUANTITY_OF_QUESTIONS));

        return trivia;

    }

}