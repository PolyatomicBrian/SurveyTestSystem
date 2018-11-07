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
        /**
         * Todo, commented values will be implemented in next homework.
         */
        //m.addMenuOptionValue("Take", "doTake");
        m.addMenuOptionValue("Edit", "doEdit");
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
        this.getUserSelectedSurvey().edit(ioService);
    }

    public void doDisplay(){
        this.getUserSelectedSurvey().display(ioService);
    }

    public void doLoad() throws IOException, ClassNotFoundException{
        // Create service used for serializing and deserializing.
        SerializingService<Survey> serializingService = new SerializingService<>();
        // List available files ending with Survey file extension.
        List<String> files = serializingService.availableFiles(this.getFileExtension());
        // Get user's choice for which file to deserialize.
        int numSelection = this.ioService.getChoiceFromUser(files);
        // Deserialize it.
        Survey loadedSurvey = serializingService.deserialize(files.get(numSelection));
        // Add it to our loaded surveys.
        this.loadedSurveys.add(loadedSurvey);
    }

    public void doSave() throws IOException {
        // Prompt user to select a survey to serialize.
        Survey surveyToSave = getUserSelectedSurvey();
        // Create service to serialize.
        SerializingService<Survey> serializingService = new SerializingService<>();
        // Create a filename and serialize object using that name.
        String fileName = surveyToSave.getSurveyName() + this.getFileExtension();
        serializingService.serialize(fileName, surveyToSave);
    }

    public void doTabulate() {
        /**
         * Todo, next homework.
         */
    }

    protected Survey getUserSelectedSurvey(){
        if (this.loadedSurveys.isEmpty()){
            throw new RuntimeException("There are no surveys loaded!");
        }
        // Prompt user to select a survey from those that have been loaded.
        return this.loadedSurveys.get(ioService.getChoiceFromUser(loadedSurveys));
    }

}
