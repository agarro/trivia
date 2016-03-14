package com.quimera.model;

import com.quimera.services.TriviaService;

import java.util.concurrent.TimeUnit;

/**
 * Created by Manu on 11/3/16.
 */
public class TriviaRunnable implements Runnable {
    @Override
    public void run() {

        if (TriviaService.getTriviaStatus().equals(TriviaStatus.READY) || TriviaService.getTriviaStatus().equals(TriviaStatus.STOPPED)) {

            TriviaService.setTriviaStatus(TriviaStatus.RUNNABLE);

            for (Question question : TriviaService.trivia.getQuestions()) {

                TriviaService.currentQuestion = question;
                try {
                    TimeUnit.SECONDS.sleep(Constant.TIME_TO_RESPONSE_EACH_QUESTION);
                } catch (InterruptedException e) {
                    TriviaService.setTriviaStatus(TriviaStatus.STOPPED);
                    TriviaService.currentQuestion = null;
                    break;
                   //TODO - implements logger
                }
            }
            TriviaService.currentQuestion = null;
            TriviaService.setTriviaStatus(TriviaStatus.READY);
        }

    }
}
