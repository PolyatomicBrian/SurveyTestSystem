package edu.drexel.brj33.cs350.service;

import edu.drexel.brj33.cs350.menu.Menu;
import edu.drexel.brj33.cs350.response.Response;
import edu.drexel.brj33.cs350.survey.Survey;
import edu.drexel.brj33.cs350.survey.Test;

import java.util.List;

public class TestService extends SurveyService {

    public TestService(IOService ioService) {
        super(ioService);
    }

    protected Menu getMenu(){
        Menu m = super.getMenu();
        m.addMenuOptionValue("Grade", "doGrade");
        return m;
    }

    public void doCreate(){
        Survey survey = new Test();
        survey.setup(ioService);
        this.loadedSurveys.add(survey);
    }

    public void doGrade() throws Exception {
        SerializingService<Test> ss = new SerializingService<>();
        List<String> listFiles = ss.availableFiles(".resp");
        for (String f : listFiles){
            // Split filename based on periods.
            String[] splitF = f.split("\\.");
            // If this file isn't named *.test.*.resp then we won't want to list it,
            // so remove it from our list.
            if (splitF.length != 4 || !splitF[1].equals("test")){
                listFiles.remove(f);
            }
        }
        // Have user select a completed test to grade.
        ioService.writeContent("Pick a Response to grade.");
        int indexFileToGrade = ioService.getChoiceFromUser(listFiles);
        // Get the test that corresponds to selected index.
        String fileNameToGrade = listFiles.get(indexFileToGrade);
        // Read in the test as an object, and grade it.
        Test t = ss.deserialize(fileNameToGrade);
        t.grade(ioService);
    }

    protected String getFileExtension(){
        return ".test";
    }

}
