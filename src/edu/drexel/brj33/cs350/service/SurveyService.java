package edu.drexel.brj33.cs350.service;

import edu.drexel.brj33.cs350.menu.Menu;
import edu.drexel.brj33.cs350.survey.Survey;

import java.util.List;

public class SurveyService {

    protected IOService ioService;
    private List<Survey> loadedSurveys;

    public SurveyService(IOService ioService) {
        this.ioService = ioService;
    }

    public void doMenu(){
        this.getMenu().handleMenu(this.ioService);
    }

    protected Menu getMenu() {
        Menu m = new Menu(this);
        m.addMenuOptionValue("Create", "doCreate");
        m.addMenuOptionValue("Take", "doTake");
        m.addMenuOptionValue("Edit", "doEdit");
        m.addMenuOptionValue("Display", "doDisplay");
        m.addMenuOptionValue("Load", "doLoad");
        m.addMenuOptionValue("Save", "doSave");
        m.addMenuOptionValue("Tabulate", "doTabulate");
        return m;
    }

    public void doCreate(){

    }

    public void doTake(){
        this.getUserSelectedSurvey().take(ioService);
    }

    public void doEdit(){

    }

    public void doDisplay(){
        this.getUserSelectedSurvey().display(ioService);
    }

    public void doLoad(){

    }

    public void doSave(){
        Survey surveyToSave = getUserSelectedSurvey();
        SerializingService<Survey> serializingService = new SerializingService<>();
        String fileName = surveyToSave.getFileName();
        serializingService.serialize(fileName, surveyToSave);
    }

    public void doTabulate(){

    }

    protected Survey getUserSelectedSurvey(){
        return this.loadedSurveys.get(ioService.getChoiceFromUser(loadedSurveys));
    }

}
