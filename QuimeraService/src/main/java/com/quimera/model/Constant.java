package com.quimera.model;

/**
 * Created by Manu on 9/2/16.
 */
public interface Constant {

    int POINTS_CORRECT_ANSWER = 5;
    int POINTS_WRONG_ANSWER = 0;

    int QUANTITY_OF_QUESTIONS = 10;

    int TIME_TO_SHOW_QUESTIONS_IN_SECONDS = 5;

    int TIME_TO_SHOW_OPTIONS_IN_SECONDS = 5;

    int TIME_TO_SHOW_PARTIAL_WINNERS_IN_SECONDS = 5;

    int TIME_TO_SHOW_FINAL_WINNERS_IN_SECONDS = 5;

    int TIME_TO_SHOW_CORRECT_ANSWER_IN_SECONDS = 5;

    int TIME_TO_SHOW_BANNER = 5;


    String READY_STATUS = "Trivia lista para empezar.";

    String SHOWING_CORRECT_ANSWER_STATUS = "Mostrando respuesta correcta.";

    String SHOWING_WINNERS_STATUS = "Fin de ronda. Mostrando ganadores.";

    String TERMINATED_STATUS = "La trivia ha finalizado. Mostrando ganadores";

    String RUNNING_STATUS = "Trivia ejecutándose";


}
