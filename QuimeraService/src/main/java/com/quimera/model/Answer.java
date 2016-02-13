package com.quimera.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

/**
 * Created by Manu on 31/1/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Answer {

    private User user;
    private Bar bar;
    private Question question;
    private String answer;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrectAnswer() {
        return question != null && question.getCorrectAnswer().equals(this.answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return Objects.equals(user, answer.user) &&
                Objects.equals(bar, answer.bar) &&
                Objects.equals(question, answer.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, bar, question);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "user=" + user +
                ", bar=" + bar +
                ", question=" + question +
                ", answer='" + answer + '\'' +
                '}';
    }
}
