package edu.drexel.brj33.cs350.survey;

import edu.drexel.brj33.cs350.response.Response;

import java.util.List;
import java.util.Set;

public class Test extends Survey {
    private List<Set<Response>> correctAnswers;

    public String getFileExtension(){
        return "test";
    }
}
