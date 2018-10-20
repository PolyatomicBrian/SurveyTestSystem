package edu.drexel.brj33.cs350.menu;


import edu.drexel.brj33.cs350.service.IOService;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    /**
     * Lists are used here so we can have a numeric index associated with
     * each method and each value. This index will be tied to what the user
     * will select when prompted to make a choice from the menu.
     */
    private List<String> optionMethods;
    private List<String> optionValues;

    private Object service;

    public Menu(Object service){
        this.service = service;
        this.optionMethods = new ArrayList<>();
        this.optionValues = new ArrayList<>();
    }

    public void addMenuOptionValue(String optionValue, String optionMethod){
        this.optionMethods.add(optionMethod);
        this.optionValues.add(optionValue);
    }

    public void handleMenu(IOService ioService){
        this.addMenuOptionValue("Quit", "doQuit");
        // Get index corresponding to a value displayed.
        // This will be used as an index for getting the associated method.
        int userChoice = ioService.getChoiceFromUser(optionValues);
        try {
            // Using Reflection, call the method associated with the selected
            // value.
            Class serviceClass = service.getClass();
            Method m = serviceClass.getMethod(optionMethods.get(userChoice));
            m.invoke(service);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }




}
