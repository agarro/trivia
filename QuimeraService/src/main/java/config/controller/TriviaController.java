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
    private static Set<Answer> answerSet = new TreeSet<>();

    @PostConstruct
    public void init() {
        trivia = generateTrivia();
        new Thread(new QuestionGenerator()).start();

    }

    @RequestMapping(value = "getQuestion", method = RequestMethod.GET)
    public
    @ResponseBody
    Question getQuestion(@RequestParam(value = "id", required = false) String idParticipant) {

        return activeQuestion;
    }

    @RequestMapping(value = "pushAnswer", method = RequestMethod.GET)
    public
    @ResponseBody
    Question pushAnswer(@RequestParam(value = "idParticipant", required = true) String idParticipant,
                        @RequestParam(value = "idBar", required = true) String idBar,
                        @RequestParam(value = "idQuestion", required = true) String idQuestion,
                        @RequestParam(value = "answer", required = true) String answer) {


        return activeQuestion;
    }


    private static Trivia generateTrivia() {
        Trivia trivia = new Trivia();
        questionsList = DataGenerator.questions();
        Collections.shuffle(questionsList);

        trivia.getQuestionList().addAll(questionsList.subList(0, 5));

        return trivia;

    }

}