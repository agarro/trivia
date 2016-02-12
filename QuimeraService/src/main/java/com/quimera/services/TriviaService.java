package com.quimera.services;

import com.quimera.model.*;
import com.quimera.repositories.QuestionRepository;
import com.quimera.repositories.UserRepository;
import com.quimera.util.QuestionGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by Manu on 12/2/16.
 */
@Component
public class TriviaService {

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

    public Question getQuestion() {
        return activeQuestion;
    }

    public Answer pushAnswer(Answer answer) {

        User user = userRepository.findOne(answer.getQuestion().getIdQuestion()); //User validation
        if (user!= null && !answerSet.contains(answer) && answer.getQuestion().getIdQuestion().equals(activeQuestion.getIdQuestion())) {
            answerSet.add(answer);
        }
        return answer;
    }

    public List<Score> getScore(Bar bar) {

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
