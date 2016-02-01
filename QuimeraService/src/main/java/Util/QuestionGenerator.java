package Util;

import config.controller.TriviaController;
import model.Question;

import java.util.concurrent.TimeUnit;

/**
 * Created by Manu on 31/1/16.
 */
public class QuestionGenerator implements Runnable {

    public void run() {
        for (Question question : TriviaController.trivia.getQuestionList()) {

            TriviaController.activeQuestion = question;
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        TriviaController.activeQuestion = null;

    }
}
