package edu.drexel.brj33.cs350.service;

import edu.drexel.brj33.cs350.io.IOHandler;

public class SurveySystemService {

    private IOHandler ioHandler;

    public SurveySystemService(IOHandler ioHandler){
        this.ioHandler = ioHandler;
    }

    public void doMenu(){

        //If user picks Survey
        SurveyService surveyService = new SurveyService(this.ioHandler);
        surveyService.doMenu();

        //If user picks Test
        surveyService  = new TestService(this.ioHandler);
        surveyService.doMenu();

    }
}
