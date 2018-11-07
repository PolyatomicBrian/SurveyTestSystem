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
    private List<MenuAction> optionActions;

    private Object service;

    public Menu(Object service){
        this.service = service; // Type of service, either Survey or Test.
        this.optionMethods = new ArrayList<>();
        this.optionValues = new ArrayList<>();
        this.optionActions = new ArrayList<>();
    }

    /**
     * Uses Reflection and calls a predefined Java method when the corresponding
     * Menu option is selected.
     */
    public void addMenuOptionValue(String optionValue, String optionMethod){
        this.optionMethods.add(optionMethod); // Java method name being used.
        this.optionValues.add(optionValue);   // Menu description.
        this.optionActions.add(null);         // Lambda expression not being used.
    }

    /**
     * Uses Lambda expressions to perform an action corresponding to a Menu option.
     */
    public void addMenuOptionAction(String optionValue, MenuAction optionAction){
        this.optionValues.add(optionValue);   // Menu description.
        this.optionActions.add(optionAction); // Lambda expression being used.
        this.optionMethods.add(null);         // Java method not being used.
    }

    public void handleMenu(IOService ioService){
        handleMenu(ioService, true);
    }

    /**
     * Displays this menu, and performs an action based on the user's input.
     */
    public void handleMenu(IOService ioService, boolean repeat){
        this.addMenuOptionValue("Done", null);
        boolean cont = true;
        while (cont) {
            if (!repeat){
                cont = false;
            }
            // Get index corresponding to a value displayed.
            // This will be used as an index for getting the associated method.
            int userChoice = ioService.getChoiceFromUser(optionValues);
            try {
                String method = optionMethods.get(userChoice);
                MenuAction action = optionActions.get(userChoice);
                if (action == null && method != null) {
                    // Using Reflection, call the method associated with the selected
                    // value.
                    Class serviceClass = service.getClass();
                    Method m = serviceClass.getMethod(optionMethods.get(userChoice));
                    m.invoke(service);
                } else if (method == null && action != null){
                    // Perform the action, ie execute the Lambda expression.
                    action.action();
                } else {
                    cont = false;
                }
            } catch (Exception e) {
                //e.printStackTrace();
                if (e.getCause() != null && e.getCause().getMessage() != null) {
                    ioService.writeContent(e.getCause().getMessage());
                }else{
                    ioService.writeContent("An unexpected error has occurred.");
                    ioService.writeContent("Please try again.");
                }
            }
        }
    }
}
