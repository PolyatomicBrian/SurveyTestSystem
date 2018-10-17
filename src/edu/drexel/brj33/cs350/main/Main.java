package edu.drexel.brj33.cs350.main;

import edu.drexel.brj33.cs350.io.IOHandler;
import edu.drexel.brj33.cs350.service.SurveySystemService;

public class Main {

    public static void main(String[] args){
        SurveySystemService sys = new SurveySystemService(new IOHandler(System.out, System.in));
        sys.doMenu();
    }
}
