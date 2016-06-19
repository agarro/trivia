package com.quimera.model;

import com.quimera.services.TriviaService;

import java.util.concurrent.TimeUnit;

/**
 * Created by Manu on 11/3/16.
 */
public class TriviaRunnable implements Runnable {
    @Override
    public void run() {

        if (TriviaService.getTriviaStatus().equals(TriviaStatus.READY) || TriviaService.getTriviaStatus().equals(TriviaStatus.TERMINATED)) {

            TriviaService.setTriviaStatus(TriviaStatus.RUNNABLE);

            for (Question question : TriviaService.trivia.getQuestions()) {

                try {
                    int currentQuestionPosition = ++TriviaService.currentQuestionPosition;
                    TriviaService.currentQuestion = question;
                    TriviaService.setTriviaStatus(TriviaStatus.SHOWING_QUESTION);

                    sleep(Constant.TIME_TO_SHOW_QUESTIONS_IN_SECONDS);

                    TriviaService.setTriviaStatus(TriviaStatus.SHOWING_OPTIONS);
                    sleep(Constant.TIME_TO_SHOW_OPTIONS_IN_SECONDS);
                    TimeUnit.SECONDS.sleep(1);

                    TriviaService.setTriviaStatus(TriviaStatus.SHOWING_CORRECT_ANSWER);
                    sleep(Constant.TIME_TO_SHOW_CORRECT_ANSWER_IN_SECONDS);

                    TriviaService.setTriviaStatus(TriviaStatus.SHOWING_DESCRIPTION);
                    sleep(Constant.TIME_TO_SHOW_DESCRIPTION_IN_SECONDS);

                    TriviaService.setTriviaStatus(TriviaStatus.SHOWING_PARTIAL_WINNERS);
                    TriviaService.currentQuestion = null;
                    sleep(Constant.TIME_TO_SHOW_PARTIAL_WINNERS_IN_SECONDS);

                    if (currentQuestionPosition == 5 || currentQuestionPosition == 10 || currentQuestionPosition == 15) {
                        TriviaService.setTriviaStatus(TriviaStatus.SHOWING_BANNER);
                        sleep(Constant.TIME_TO_SHOW_BANNER);
                    }
                    if (currentQuestionPosition == 15) {
                        TriviaService.setTriviaStatus(TriviaStatus.SHOWING_FINAL_WINNERS);
                        sleep(Constant.TIME_TO_SHOW_FINAL_WINNERS_IN_SECONDS);
                        break;
                    }
                } catch (InterruptedException e) {
                    //TODO - implements logger
                    break;

                }

            }
            TriviaService.currentQuestion = null;
            TriviaService.currentQuestionPosition = 0;
            TriviaService.setTriviaStatus(TriviaStatus.TERMINATED);

        }

    }

    private static void sleep(int seconds) throws InterruptedException {


        do {
            TriviaService.elapsedTime = seconds--;
            TimeUnit.SECONDS.sleep(1);
        } while (seconds >= 0);

    }


}
