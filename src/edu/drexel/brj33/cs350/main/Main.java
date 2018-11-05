package edu.drexel.brj33.cs350.main;

import edu.drexel.brj33.cs350.service.IOService;
import edu.drexel.brj33.cs350.service.SurveySystemService;

public class Main {

    public static void main(String[] args){
        // Create our driver, and begin using the Survey System.
        SurveySystemService sys = new SurveySystemService(new IOService(System.out, System.in));
        sys.doMenu();
    }
}
