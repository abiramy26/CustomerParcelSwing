package controller;

import model.Manager;
import view.CustomerView;
import view.ParcelView;

public class MainController {
    private Manager manager;

    public MainController() {
        this.manager = new Manager();
    }

    public void startCustomerManagement() {
        // Initialize CustomerController and pass it to the CustomerView
        CustomerController customerController = new CustomerController(manager);
        new CustomerView(customerController);
    }

    public void startParcelManagement() {
        // Initialize ParcelController and pass it to the ParcelView
        ParcelController parcelController = new ParcelController(manager);
        new ParcelView(parcelController);
    }

}
