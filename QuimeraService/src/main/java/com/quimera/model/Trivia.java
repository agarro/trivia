package com.quimera.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Manu on 31/1/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class Trivia {

    @Id
    private String idTrivia;
    private int rounds;
    private String name;
    @DBRef
    private List<Banner> banners;
    @DBRef
    private List<Question> questions;
    @DBRef
    private Category category;

    @DBRef
    private Subcategory subcategory;

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Banner> getBanners() {
        if (banners == null) {
            banners = new ArrayList<>();
        }
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<Question> getQuestions() {
        if (questions == null) {
            questions = new ArrayList<>();
        }
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdTrivia() {
        return idTrivia;
    }

    public void setIdTrivia(String idTrivia) {
        this.idTrivia = idTrivia;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trivia)) return false;
        Trivia trivia = (Trivia) o;
        return rounds == trivia.rounds &&
                Objects.equals(idTrivia, trivia.idTrivia) &&
                Objects.equals(name, trivia.name) &&
                Objects.equals(questions, trivia.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTrivia, rounds, name, questions);
    }
}
