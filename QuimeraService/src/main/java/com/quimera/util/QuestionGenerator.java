package com.quimera.util;

import com.quimera.model.Constant;
import com.quimera.model.Question;
import com.quimera.services.TriviaService;

import java.util.concurrent.TimeUnit;

/**
 * Created by Manu on 31/1/16.
 */
public class QuestionGenerator implements Runnable {

    public void run() {
        for (Question question : TriviaService.trivia.getQuestionList()) {

            TriviaService.activeQuestion = question;
            try {
                TimeUnit.SECONDS.sleep(Constant.TIME_TO_RESPONSE_EACH_QUESTION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        TriviaService.activeQuestion = null;

    }
}
