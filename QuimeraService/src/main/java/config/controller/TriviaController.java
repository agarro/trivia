package config.controller;

import Util.DataGenerator;
import Util.QuestionGenerator;
import model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by Manu on 31/1/16.
 */
@Controller
@RequestMapping("/trivia")
public class TriviaController {

    private static List<Question> questionsList;
    public static Trivia trivia;
    public static Question activeQuestion;
    private static int numberOfQuestion;
    private static Set<Participant> participantSet = new HashSet<>();
    private static Map<String, Bar> barList = new HashMap<>();
    private static Set<Answer> answerSet = new HashSet<>();

    @PostConstruct
    public void init() {
        trivia = generateTrivia();
        new Thread(new QuestionGenerator()).start();

    }

    @RequestMapping(value = "getQuestion", method = RequestMethod.GET)
    public
    @ResponseBody
    Question getQuestion() {

        return activeQuestion;
    }

    @RequestMapping(value = "pushAnswer", method = RequestMethod.GET)
    public
    @ResponseBody
    Question pushAnswer(@RequestParam(value = "idParticipant", required = true) String idParticipant,
                        @RequestParam(value = "idBar", required = true) String idBar,
                        @RequestParam(value = "idQuestion", required = true) String idQuestion,
                        @RequestParam(value = "answer", required = true) String answer) {

        Answer answer1 = new Answer();
        answer1.setAnswer(answer);
        answer1.setBar(barList.get(idBar));
        Participant participant = new Participant(idParticipant);
        participantSet.add(participant);
        answer1.setParticipant(participant);
        answer1.setQuestion(findQuestion(idQuestion));
        if (!answerSet.contains(answer1) && idQuestion.equals(activeQuestion.getIdQuestion())) {
            answerSet.add(answer1);
        }
        return activeQuestion;
    }

    @RequestMapping(value = "getPoints", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Point> getPoints() {

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

    private Question findQuestion(String idQuestion) {

        for (Question questionAux :
                questionsList) {
            if (questionAux.getIdQuestion().equals(idQuestion)) {
                return questionAux;
            }
        }
        return null;

    }


    private static Trivia generateTrivia() {
        Trivia trivia = new Trivia();
        questionsList = DataGenerator.questions();
        Collections.shuffle(questionsList);

        trivia.getQuestionList().addAll(questionsList.subList(0, 5));

        return trivia;

    }

}