package edu.drexel.brj33.cs350.service;

import edu.drexel.brj33.cs350.menu.Menu;
import edu.drexel.brj33.cs350.survey.Survey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SurveyService {

    protected IOService ioService;
    protected List<Survey> loadedSurveys;

    public SurveyService(IOService ioService) {
        this.ioService = ioService;
        this.loadedSurveys = new ArrayList<>();
    }

    public void doMenu(){
        this.getMenu().handleMenu(this.ioService);
    }

    protected Menu getMenu() {
        Menu m = new Menu(this);
        m.addMenuOptionValue("Create", "doCreate");
        // Lines commented out are TODO.
        //m.addMenuOptionValue("Take", "doTake");
        //m.addMenuOptionValue("Edit", "doEdit");
        m.addMenuOptionValue("Display", "doDisplay");
        m.addMenuOptionValue("Load", "doLoad");
        m.addMenuOptionValue("Save", "doSave");
        //m.addMenuOptionValue("Tabulate", "doTabulate");
        return m;
    }

    protected String getFileExtension(){
        return ".survey";
    }

    public void doCreate(){
        Survey survey = new Survey();
        survey.setup(ioService);
        this.loadedSurveys.add(survey);
    }

    public void doTake(){
        this.getUserSelectedSurvey().take(ioService);
    }

    public void doEdit(){

    }

    public void doDisplay(){
        this.getUserSelectedSurvey().display(ioService);
    }

    public void doLoad() throws IOException, ClassNotFoundException{
        SerializingService<Survey> serializingService = new SerializingService<>();
        List<String> files = serializingService.availableFiles(this.getFileExtension());
        int numSelection = this.ioService.getChoiceFromUser(files);
        Survey loadedSurvey = serializingService.deserialize(files.get(numSelection));
        this.loadedSurveys.add(loadedSurvey);
    }

    public void doSave() throws IOException {
        Survey surveyToSave = getUserSelectedSurvey();
        SerializingService<Survey> serializingService = new SerializingService<>();
        String fileName = surveyToSave.getSurveyName() + this.getFileExtension();
        serializingService.serialize(fileName, surveyToSave);
    }

    public void doTabulate(){

    }

    protected Survey getUserSelectedSurvey(){
        if (this.loadedSurveys.isEmpty()){
            throw new RuntimeException("There are no surveys loaded!");
        }
        return this.loadedSurveys.get(ioService.getChoiceFromUser(loadedSurveys));
    }

}
