package edu.drexel.brj33.cs350.survey;

import edu.drexel.brj33.cs350.service.IOService;
import edu.drexel.brj33.cs350.question.Question;
import edu.drexel.brj33.cs350.service.SerializingInterface;

import java.util.ArrayList;
import java.util.List;

public class Survey implements SerializingInterface {
    private List<Question> questions;
    private String surveyName;
    public static final String fileExtension = "survey";

    public Survey(){
        this.questions = new ArrayList<>();
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    public void setup(IOService ioService){

    }

    public String getSurveyName(){
        return this.surveyName;
    }

    public void setSurveyName(String surveyName){
        this.surveyName = surveyName;
    }

    public String getFileName(){
        return getSurveyName() + "." + getFileExtension();
    }

    public void display(IOService ioService) {
        for (Question q : this.questions){
            q.display(ioService);
        }
    }

    public void take(IOService ioService){
        for (Question q : this.questions){
            q.take(ioService);
        }
    }

    public String getFileExtension(){
        return "survey";
    }



}
