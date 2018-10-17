package edu.drexel.brj33.cs350.survey;

import edu.drexel.brj33.cs350.question.Question;

import java.util.ArrayList;
import java.util.List;

public class Survey {
    private List<Question> questions;

    public Survey(){
        this.questions = new ArrayList<>();
    }

    public List<Question> getQuestions() {
        return this.questions;
    }
}
