package com.quimera.util;

import com.quimera.model.Bar;
import com.quimera.model.Question;
import com.quimera.model.Trivia;
import com.quimera.model.User;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
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
        user1.setFirstName("Juan");
        user1.setLastName("Riquelme");
        user1.setUsername("juanriquelme");
        user1.setPassword("juanri");

        User user2 = new User();
        user2.setIdUser("2");
        user2.setEmail("oscar@gmail.com");
        user2.setFirstName("Oscar");
        user2.setLastName("Caceres");
        user2.setUsername("oscarcaceres");
        user2.setPassword("caceosca");

        User user3 = new User();
        user3.setIdUser("3");
        user3.setEmail("pedro@hotmail.com");
        user3.setFirstName("Pedro");
        user3.setLastName("Bogado");
        user3.setUsername("bogadopedro");
        user3.setPassword("bopedro");

        User user4 = new User();
        user4.setIdUser("4");
        user4.setEmail("alejandro@outlook.com");
        user4.setFirstName("Alejandro");
        user4.setLastName("Garro");
        user4.setUsername("garroalejandro");
        user4.setPassword("gale");


        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        return userList;
    }

    public static List<Trivia> triviaExamples() {

        List<Trivia> trivias = new ArrayList<>();
        List<Question> questions = questionsExamples();

        Trivia trivia1 = new Trivia();

        trivia1.setName("trivia 1");

        Collections.shuffle(questions);
        trivia1.setQuestions(questions.subList(0,5));

        Trivia trivia2 = new Trivia();
        trivia2.setName("trivia 2");

        Collections.shuffle(questions);
        trivia2.setQuestions(questions.subList(0,5));

        trivias.add(trivia1);
        trivias.add(trivia2);

        return trivias;
    }

    public void generatePolicyAndSignature() throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {

        String policy_document = "{" +
                "\"expiration\": \"2020-01-01T00:00:00Z\",\n" +
                "  \"conditions\": [ \n" +
                "    {\"bucket\": \"quimera-web-admin-banners\"}, \n" +
                "    [\"starts-with\", \"$key\", \"uploads/\"],\n" +
                "    {\"acl\": \"public-read\"},\n" +
                "    {\"success_action_redirect\": \"/banner/successful-upload\"},\n" +
                "    [\"starts-with\", \"$Content-Type\", \"\"],\n" +
                "    [\"content-length-range\", 0, 1048576]\n" +
                "  ]\n" +
                "}";

        String aws_secret_key = "6zn0Foc6NWrk0LuKWgwJfYOeNXbpjardkNywjPk4";

        String policy = (new BASE64Encoder()).encode(
                policy_document.getBytes("UTF-8")).replaceAll("\n", "").replaceAll("\r", "");

        Mac hmac = Mac.getInstance("HmacSHA1");
        hmac.init(new SecretKeySpec(
                aws_secret_key.getBytes("UTF-8"), "HmacSHA1"));
        String signature = (new BASE64Encoder()).encode(
                hmac.doFinal(policy.getBytes("UTF-8")))
                .replaceAll("\n", "");

        System.out.println(policy);
        System.out.println(signature);
    }

}
