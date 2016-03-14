package com.quimera.services;

import com.quimera.model.*;
import com.quimera.repositories.QuestionRepository;
import com.quimera.repositories.TriviaRepository;
import com.quimera.repositories.UserRepository;
import com.quimera.util.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Manu on 12/2/16.
 */
@Component
public class TriviaService {

    public static Trivia trivia;
    public static Question currentQuestion;
    private static Set<Answer> answerSet = new HashSet<>();

    private static TriviaStatus triviaStatus = TriviaStatus.READY;

    @SuppressWarnings("SpringJavaAutowiringInspection")

    @Autowired
    private TriviaRepository triviaRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserRepository userRepository;
    private Thread currentTrivia = new Thread(new TriviaRunnable());

    @PostConstruct
    private void init(){
        triviaRepository.save(DataGenerator.triviaExamples());
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

    public Trivia generateTrivia() {

        Trivia trivia = new Trivia();

        List<Question> questionsList = questionRepository.findAll();
        Collections.shuffle(questionsList);
        int quantityOfQuestions = Constant.QUANTITY_OF_QUESTIONS;
        if (questionsList.size() < Constant.QUANTITY_OF_QUESTIONS) {
            quantityOfQuestions = questionsList.size();
        }
        trivia.getQuestions().addAll(questionsList.subList(0, quantityOfQuestions));

        return trivia;

    }

    public Message stopTrivia() {

        Message message = new Message();
        if (getTriviaStatus().equals(TriviaStatus.RUNNABLE)) {
            setTriviaStatus(TriviaStatus.STOP);
            currentTrivia.interrupt();
            try {
                currentTrivia.join();
            } catch (InterruptedException e) {
                //TODO - logger
            }
            message.setMessage(getTriviaStatus().name());
        } else if (getTriviaStatus().equals(TriviaStatus.STOPPED) || getTriviaStatus().equals(TriviaStatus.READY)) {
            message.setMessage("No hay trivia ejecutándose.");
        }

        return message;
    }

    public Message startTrivia() {

        Message message = new Message();

        if (currentTrivia.getState().equals(Thread.State.TERMINATED)) {
            currentTrivia = new Thread(new TriviaRunnable());
        }

        if (currentTrivia.getState().equals(Thread.State.NEW)) {
            trivia = generateTrivia();
            currentTrivia.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                //TODO - logger
            }
            message.setMessage(getTriviaStatus().name());
        } else if (TriviaService.getTriviaStatus().equals(TriviaStatus.RUNNABLE)) {
            message.setMessage("La trivia ya esta iniciada. Finalice la trivia para volver a empezar otra.");
        }

        return message;
    }

    public Message statusTrivia() {
        return new Message(getTriviaStatus().name());
    }

    public static TriviaStatus getTriviaStatus() {
        return triviaStatus;
    }

    public static void setTriviaStatus(TriviaStatus triviaStatus) {
        TriviaService.triviaStatus = triviaStatus;
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
}
