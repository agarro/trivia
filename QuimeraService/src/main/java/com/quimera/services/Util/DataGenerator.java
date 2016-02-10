package com.quimera.services.util;

import com.quimera.services.model.Bar;
import com.quimera.services.model.User;
import com.quimera.services.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manu on 31/1/16.
 */
public class DataGenerator {


    public static List<Question> questionsExamples() {

        List<Question> questionSet = new ArrayList<>();

        Question question1 = new Question();
        question1.setIdQuestion("1");
        question1.setQuestion("¿Cuál es la capital de Argentina?");
        question1.addOption("Catamarca");
        question1.addOption("Buenos Aires");
        question1.addOption("Madrid");
        question1.addOption("Bogotá");
        question1.addOption("Córdoba");
        question1.setCorrectAnswer("Buenos Aires");

        Question question2 = new Question();
        question2.setIdQuestion("2");
        question2.setQuestion("¿En qué ciudad nació el jugador Lionel Messi?");
        question2.addOption("Rosario");
        question2.addOption("Mar del Plata");
        question2.addOption("Santiago de Chile");
        question2.addOption("Calamuchita");
        question2.addOption("Asunción");
        question2.setCorrectAnswer("Rosario");

        Question question3 = new Question();
        question3.setIdQuestion("3");
        question3.setQuestion("¿Quién fue el fundador de Microsoft Windows?");
        question3.addOption("Steve Jobs");
        question3.addOption("Bill Gates");
        question3.addOption("Von Neumann");
        question3.addOption("Time Berners-Lee");
        question3.addOption("Laurence Ellison");
        question3.setCorrectAnswer("Bill Gates");

        Question question4 = new Question();
        question4.setIdQuestion("4");
        question4.setQuestion("¿En qué año murió Juan Domingo Perón?");
        question4.addOption("1989");
        question4.addOption("1969");
        question4.addOption("1810");
        question4.addOption("1983");
        question4.addOption("1974");
        question4.setCorrectAnswer("1974");

        Question question5 = new Question();
        question5.setIdQuestion("5");
        question5.setQuestion("¿En qué año se fundó Córdoba?");
        question5.addOption("1685");
        question5.addOption("1573");
        question5.addOption("1825");
        question5.addOption("1481");
        question5.addOption("1560");
        question5.setCorrectAnswer("1573");

        Question question6 = new Question();
        question6.setIdQuestion("6");
        question6.setQuestion("¿En qué año Argentina ganó su primer campeonato mundial de fútbol?");
        question6.addOption("1994");
        question6.addOption("1982");
        question6.addOption("1978");
        question6.addOption("2002");
        question6.addOption("1929");
        question6.setCorrectAnswer("1974");

        Question question7 = new Question();
        question7.setIdQuestion("7");
        question7.setQuestion("¿Cuántos metros mide la Torre Eiffel?");
        question7.addOption("250");
        question7.addOption("300");
        question7.addOption("350");
        question7.addOption("400");
        question7.addOption("450");
        question7.setCorrectAnswer("300");

        Question question15 = new Question();
        question15.setIdQuestion("15");
        question15.setQuestion("¿En qué ciudad nació Domingo Faustino Sarmiento?");
        question15.addOption("Buenos Aires");
        question15.addOption("La Plata");
        question15.addOption("Medellín");
        question15.addOption("San Juan");
        question15.addOption("Formosa");
        question15.setCorrectAnswer("San Juan");

        questionSet.add(question1);
        questionSet.add(question2);
        questionSet.add(question3);
        questionSet.add(question4);
        questionSet.add(question5);
        questionSet.add(question6);
        questionSet.add(question7);
        questionSet.add(question15);
        return questionSet;

    }

    public static List<Bar> barsExamples() {
        List<Bar> barList = new ArrayList<>();

        Bar bar1 = new Bar();
        bar1.setAddress("Cordoba");
        bar1.setIdBar("1");
        bar1.setName("Johnny B Good");


        Bar bar2 = new Bar();
        bar2.setAddress("Cordoba");
        bar2.setIdBar("2");
        bar2.setName("Il Gato Tratorias");


        Bar bar3 = new Bar();
        bar3.setAddress("Cordoba");
        bar3.setIdBar("3");
        bar3.setName("María María");


        Bar bar4 = new Bar();
        bar4.setAddress("Cordoba");
        bar4.setIdBar("4");
        bar4.setName("La Colmena");

        barList.add(bar1);
        barList.add(bar2);
        barList.add(bar3);
        barList.add(bar4);

        return barList;
    }

    public static List<User> usersExamples() {

        List<User> userList = new ArrayList<>();

        User user1 = new User();
        user1.setIdUser("1");
        user1.setEmail("juan@yahoo.com");
        user1.setName("Juan");
        user1.setLastName("Riquelme");
        user1.setPassword("juanri".toCharArray());

        User user2 = new User();
        user2.setIdUser("2");
        user2.setEmail("oscar@gmail.com");
        user2.setName("Oscar");
        user2.setLastName("Caceres");
        user2.setPassword("caceosca".toCharArray());

        User user3 = new User();
        user3.setIdUser("3");
        user3.setEmail("pedro@hotmail.com");
        user3.setName("Pedro");
        user3.setLastName("Bogado");
        user3.setPassword("bopedro".toCharArray());

        User user4 = new User();
        user4.setIdUser("4");
        user4.setEmail("alejandro@outlook.com");
        user4.setName("Alejandro");
        user4.setLastName("Garro");
        user4.setPassword("gale".toCharArray());


        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        return userList;
    }

}
