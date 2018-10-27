package edu.drexel.brj33.cs350.service;

import edu.drexel.brj33.cs350.menu.Menu;

public class TestService extends SurveyService {

    public TestService(IOService ioService) {
        super(ioService);
    }

    protected Menu getMenu(){
        Menu m = super.getMenu();
        //m.addMenuOptionValue("Grade", "doGrade");
        return m;
    }

    public void doGrade(){

    }

}
