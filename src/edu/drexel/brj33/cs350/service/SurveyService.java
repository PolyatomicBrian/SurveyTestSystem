package edu.drexel.brj33.cs350.service;

import edu.drexel.brj33.cs350.menu.Menu;
import edu.drexel.brj33.cs350.question.Question;
import edu.drexel.brj33.cs350.response.Response;
import edu.drexel.brj33.cs350.survey.Survey;
import edu.drexel.brj33.cs350.survey.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyService {

    private String responseFileExtension = ".resp";

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
        m.addMenuOptionValue("Take", "doTake");
        m.addMenuOptionValue("Edit", "doEdit");
        m.addMenuOptionValue("Display", "doDisplay");
        m.addMenuOptionValue("Load", "doLoad");
        m.addMenuOptionValue("Save", "doSave");
        m.addMenuOptionValue("Tabulate", "doTabulate");
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

    public void doTake() throws Exception {
        Survey s = this.getUserSelectedSurvey();
        s.take(ioService);
        String userLastName = ioService.getStringFromUser("[Saving Answers] Enter your last name.");
        // Make sure userLastName does not have a period. This would interfere with
        // how we parse files later on.
        while (userLastName.contains(".")) {
            ioService.writeContent("Names cannot contain periods \".\"!");
            userLastName = ioService.getStringFromUser("Enter your last name.");
        }
        this.save(s, "." + userLastName + responseFileExtension);
        // Clear responses so user can take the survey again.
        for (Question q : s.getQuestions()){
            q.clearResponses();
        }
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
        this.save(getUserSelectedSurvey(), "");
    }

    public void save(Survey surveyToSave, String filepostfix) throws IOException {
        // Create service to serialize.
        SerializingService<Survey> serializingService = new SerializingService<>();
        // Create a filename and serialize object using that name.
        String fileName;
        if (!filepostfix.isEmpty()) {
            fileName = surveyToSave.getSurveyName() + this.getFileExtension() + filepostfix;
        }else{
            fileName = surveyToSave.getSurveyName() + this.getFileExtension();
        }
        serializingService.serialize(fileName, surveyToSave);
    }

    public void doTabulate() throws Exception {
        SerializingService<Survey> ss = new SerializingService<>();
        List<String> listFiles = ss.availableFiles(".resp");
        /* Create map to keep count of the number of responses per survey.
           The key String will be the Survey's filename.
           The value List<Survey> will be responses for that Survey.*/
        Map<String,List<Survey>> availableResponses = getAvailableResponses(listFiles, ss);
        // Create list containing all the survey names, using the keys of our map.
        List<String> listSurveys = new ArrayList<String>(availableResponses.keySet());
        int indexOfSurvey = ioService.getChoiceFromUser(listSurveys);
        // Get list of all the Surveys we need to tabulate from our map.
        List<Survey> surveysToTabulate = availableResponses.get(listSurveys.get(indexOfSurvey));
        // Create map that will contain a Question Index and an inner map containing a Response
        //   with the tallied number of responses that specific response got (the tabulation).
        Map<Integer,Map<Response,Integer>> mapQuestionsWithResponses = new HashMap<>();
        // Iterate through our surveys we plan on tabulating...
        for (Survey s : surveysToTabulate){
            // Iterate through all the questions that survey has...
            for (int i = 0; i < s.getQuestions().size(); i++) {
                Question q = s.getQuestions().get(i);
                // If the map doesn't have this question's index, add its index to the map.
                if (!mapQuestionsWithResponses.containsKey(i)){
                    mapQuestionsWithResponses.put(i, new HashMap<>());
                }
                // Iterate through all responses of that question...
                for (Response r : q.getResponses()){
                    // Create inner map.
                    Map<Response,Integer> responsesWithCounts = mapQuestionsWithResponses.get(i);
                    // If the inner map already has this response...
                    if (responsesWithCounts.containsKey(r)){
                        // Increment the tally for how many people responded with this.
                        int count = responsesWithCounts.get(r);
                        count++;
                        responsesWithCounts.put(r, count);
                    }else{
                        // This is the first person who responded with this, so set tally to 1.
                        responsesWithCounts.put(r, 1);
                    }
                }
            }
        }
        // Display tabulation:
        ioService.writeSeparator();
        for (int i = 0; i < surveysToTabulate.get(0).getQuestions().size(); i++){
            // Display question prompt.
            ioService.writeTitle(surveysToTabulate.get(0).getQuestions().get(i).getPrompt().getPromptText());
            // Display Response with the tally of how many people gave that same response.
            for (Map.Entry<Response,Integer> e : mapQuestionsWithResponses.get(i).entrySet()){
                ioService.writeIndentedContent(e.getKey() + ": " + e.getValue());
            }
            ioService.writeSeparator();
        }
    }

    private Map<String, List<Survey>> getAvailableResponses(List<String> listFiles, SerializingService<Survey> ss) throws Exception{
        Map<String,List<Survey>> availableResponses = new HashMap<>();
        List<String> availableFiles = new ArrayList<>();
        for (String f : listFiles){
            // Split filename based on periods.
            String[] splitF = f.split("\\.");
            // Make sure file follows our pattern *.*.*.resp
            if (splitF.length == 4 && splitF[1].equals(this.getFileExtension().substring(1))) {
                // Get the filename of the Survey that the response was for.
                String surveyName = splitF[0] + "." + splitF[1];
                // If survey filename is already in our map, add Survey to our list.
                if (availableResponses.containsKey(surveyName)){
                    availableResponses.get(surveyName).add(ss.deserialize(f));
                }else{
                    // If survey filename is not in our map, add it, and create an empty list.
                    availableResponses.put(surveyName, new ArrayList<>());
                    // Add current survey to map's list.
                    availableResponses.get(surveyName).add(ss.deserialize(f));
                }
                // File adheres to *.*.*.resp, so add it to our list.
                availableFiles.add(f);
            }
        }
        listFiles.clear();
        listFiles.addAll(availableFiles);
        return availableResponses;
    }

    protected Survey getUserSelectedSurvey(){
        if (this.loadedSurveys.isEmpty()){
            throw new RuntimeException("There are no surveys loaded!");
        }
        // Prompt user to select a survey from those that have been loaded.
        return this.loadedSurveys.get(ioService.getChoiceFromUser(loadedSurveys));
    }

}
