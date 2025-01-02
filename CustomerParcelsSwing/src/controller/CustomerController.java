package controller;

import model.Customer;
import model.Manager;

import java.io.IOException;
import java.util.List;


public class CustomerController {
	private Manager manager;

    public CustomerController(Manager manager) {
        this.manager = manager;
    }

    // Load customers from CSV file.
    public void loadCustomersFromCsv(String filePath) throws IOException {
        manager.loadCustomersFromCsv(filePath);
    }

    // Get a list of all customers.
    public List<Customer> getCustomers() {
        return manager.getCustomers();
    }

    // Add a new customer to the queue.
    public void addCustomer(Customer customer) {
        manager.getQueueOfCustomers().addCustomer(customer);
    }

    // Remove a customer from the queue.
    public Customer removeCustomer() {
        return manager.getQueueOfCustomers().removeCustomer();
    }
}
