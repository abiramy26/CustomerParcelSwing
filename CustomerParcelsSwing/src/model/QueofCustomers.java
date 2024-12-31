package model;

import java.util.Queue;


//manages a queue of customers using a LinkedList.
public class QueofCustomers {

	private Queue<Customer> customerQueue;

    public QueofCustomers() {
        customerQueue = new java.util.LinkedList<>();
    }

    public void addCustomer(Customer customer) {
        customerQueue.add(customer);
    }

    public Customer removeCustomer() {
        return customerQueue.poll(); // Removes and returns the head of the queue
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
    
}
