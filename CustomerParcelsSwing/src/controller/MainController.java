package controller;

import model.Manager;
import view.CustomerView;

public class MainController {

	private Manager manager;

    public MainController() {
        this.manager = new Manager();
    }

    public void startApplication() {
        // Initialize CustomerController and pass it to the CustomerView
        CustomerController customerController = new CustomerController(manager);
        new CustomerView(customerController);
    }
    
}
