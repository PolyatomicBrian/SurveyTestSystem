package edu.drexel.brj33.cs350.service;

import edu.drexel.brj33.cs350.menu.Menu;
import edu.drexel.brj33.cs350.survey.Survey;
import edu.drexel.brj33.cs350.survey.Test;

public class TestService extends SurveyService {

    public TestService(IOService ioService) {
        super(ioService);
    }

    protected Menu getMenu(){
        Menu m = super.getMenu();
        //m.addMenuOptionValue("Grade", "doGrade");
        return m;
    }

    public void doCreate(){
        Survey survey = new Test();
        survey.setup(ioService);
        this.loadedSurveys.add(survey);
    }

    public void doGrade(){
        /**
         * Todo, next homework.
         */
    }

    protected String getFileExtension(){
        return ".test";
    }

}
