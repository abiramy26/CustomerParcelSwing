package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Queue;


//manages a queue of customers using a LinkedList.
public class QueofCustomers {

	private Queue<Customer> customerQueue;
	private String csvFilePath = "C:\\Users\\User\\Downloads\\java course work\\java course work\\Custs.csv";
	private Manager manager;
    public QueofCustomers() {
        customerQueue = new java.util.LinkedList<>();
//        try {
//			manager.loadCustomersFromCsv(csvFilePath);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }

    public void addCustomer(Customer customer) {
        customerQueue.add(customer);
        saveCustomersToCsv();
    }
    
    public void bulkAddCustomers(List<Customer> customers) {
        customerQueue.addAll(customers);
    }
    public Customer removeCustomer() {
    	Customer removedCustomer = customerQueue.poll();
        saveCustomersToCsv();
        return removedCustomer;
//        return customerQueue.poll(); // Removes and returns the head of the queue
    }

    public Customer peekCustomer() {
        return customerQueue.peek(); // Returns the head of the queue without removing it
    }

    public boolean isEmpty() {
        return customerQueue.isEmpty();
    }

    public int size() {
        return customerQueue.size();
    }
    
    private void saveCustomersToCsv() {
        if (csvFilePath == null || csvFilePath.isEmpty()) {
            System.err.println("CSV file path is not set!");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            for (Customer customer : customerQueue) {
                writer.write(customer.getName() + "," + customer.getParcelId());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Sets the CSV file path if it needs to be updated dynamically
    public void setCsvFilePath(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }
    
    public void clearQueue() {
        customerQueue.clear();
    }
}
