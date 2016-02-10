package com.quimera.services.util;

import com.quimera.services.controller.TriviaController;
import com.quimera.services.model.Constant;
import com.quimera.services.model.Question;

import java.util.concurrent.TimeUnit;

/**
 * Created by Manu on 31/1/16.
 */
public class QuestionGenerator implements Runnable {

    public void run() {
        for (Question question : TriviaController.trivia.getQuestionList()) {

            TriviaController.activeQuestion = question;
            try {
                TimeUnit.SECONDS.sleep(Constant.TIME_TO_RESPONSE_EACH_QUESTION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        TriviaController.activeQuestion = null;

    }
}
