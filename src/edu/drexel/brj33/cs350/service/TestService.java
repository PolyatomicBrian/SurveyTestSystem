package edu.drexel.brj33.cs350.service;

import edu.drexel.brj33.cs350.menu.Menu;
import edu.drexel.brj33.cs350.response.Response;
import edu.drexel.brj33.cs350.survey.Survey;
import edu.drexel.brj33.cs350.survey.Test;

import java.util.ArrayList;
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
        List<String> availableFiles = ss.availableFiles(".resp");
        List<String> listFiles = new ArrayList<>();
        for (String f : availableFiles){
            // Split filename based on periods.
            String[] splitF = f.split("\\.");
            // If this file is named *.test.*.resp then it's (probably) valid, so add it to our list.
            //   The reason it would not be valid is if the user tried to create their own *.test.*.resp file,
            //   which is out of scope for this program.
            if (splitF.length == 4 && splitF[1].equals("test")){
                listFiles.add(f);
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
