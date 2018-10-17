package edu.drexel.brj33.cs350.service;

import edu.drexel.brj33.cs350.io.IOHandler;
import edu.drexel.brj33.cs350.question.Question;
import edu.drexel.brj33.cs350.survey.Survey;

public class SurveyService {

    protected IOHandler ioHandler;

    public SurveyService(IOHandler ioHandler){
        this.ioHandler = ioHandler;
    }

    public void doMenu(){

    }

    public void displaySurvey(Survey s){
        for(Question q : s.getQuestions()){
            this.ioHandler.displayQuestion(q);
        }
    }
}
