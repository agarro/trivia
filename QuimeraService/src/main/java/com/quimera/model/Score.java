package com.quimera.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Manu on 1/2/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Score implements Comparable<Score> {

    private User user;
    private int score;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Score score) {
        return new Integer(this.score).compareTo(score.getScore());
    }
}
