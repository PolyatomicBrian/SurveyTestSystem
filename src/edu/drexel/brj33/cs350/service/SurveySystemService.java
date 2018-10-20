package edu.drexel.brj33.cs350.service;

import edu.drexel.brj33.cs350.menu.Menu;

public class SurveySystemService {

    private IOService ioService;

    public SurveySystemService(IOService ioService){
        this.ioService = ioService;
    }

    public void doMenu(){
        Menu m = new Menu(this);
        m.addMenuOptionValue("SurveyMode", "doSurveyMode");
        m.addMenuOptionValue("TestMode", "doTestMode");
        m.handleMenu(this.ioService);
    }

    public void doSurveyMode(){
        SurveyService surveyService = new SurveyService(ioService);
        surveyService.doMenu();
    }

    public void doTestMode(){
        TestService testService = new TestService(ioService);
        testService.doMenu();
    }

}
