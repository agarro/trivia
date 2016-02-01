package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manu on 31/1/16.
 */
public class Trivia {

    private List<Question> questionList = new ArrayList<>();

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void addQuestion(Question question) {
        this.questionList.add(question);
    }
}