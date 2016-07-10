package com.quimera.services;

import com.quimera.model.*;
import com.quimera.repositories.QuestionRepository;
import com.quimera.repositories.TriviaRepository;
import com.quimera.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Manu on 12/2/16.
 */
@Component
@SuppressWarnings("SpringJavaAutowiringInspection")
public class TriviaService {

    public static Trivia trivia;
    public static Question currentQuestion;
    private static Set<Answer> answerSet = new HashSet<>();

    @Autowired
    private TriviaRepository triviaRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    public void insertAll(List<Trivia> trivias) {
        triviaRepository.insert(trivias);
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public Answer pushAnswer(Answer answer) {

        User user = userRepository.findOne(answer.getQuestion().getIdQuestion()); //User validation
        if (user != null && !answerSet.contains(answer) && answer.getQuestion().getIdQuestion().equals(currentQuestion.getIdQuestion())) {
            answerSet.add(answer);
        }
        return answer;
    }

    public List<Score> getScore(Bar bar) {

        HashMap<String, Score> scoreHashMap = new HashMap<>();

        for (Answer answer : answerSet) {
//            if (answer.getBar().equals(bar)) {
//                String idParticipant = answer.getPlayer().getIdUser();
//                Score score;
//                if (scoreHashMap.containsKey(idParticipant)) {
//                    score = scoreHashMap.get(idParticipant);
//                } else {
//                    score = new Score();
//                    score.setUser(answer.getPlayer());
//                }
//                score.setScore(score.getScore() + (answer.isCorrectAnswer() ? Constant.POINTS_CORRECT_ANSWER : Constant.POINTS_WRONG_ANSWER));
//                scoreHashMap.put(idParticipant, score);
//            }
        }
        List<Score> scoresList = new ArrayList<>(scoreHashMap.values());
        Collections.sort(scoresList);

        return scoresList;
    }

    public void insert(Trivia trivia) {
        triviaRepository.insert(trivia);
    }

    public void update(Trivia trivia) {
        triviaRepository.save(trivia);
    }

    public List<Trivia> findAll() {
        return triviaRepository.findAll();
    }

    public Trivia find(String id) {
        return triviaRepository.findOne(id);
    }

    public void delete(Trivia trivia) {
        triviaRepository.delete(trivia);
    }

    public List<Trivia> findByTriviaStatus(TriviaStatus triviaStatus){
        return triviaRepository.findByTriviaStatus(triviaStatus);
    }

}
