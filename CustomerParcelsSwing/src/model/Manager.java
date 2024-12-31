package model;

import java.io.IOException;

public class Manager {
	private QueofCustomers queueOfCustomers;
    private ParcelMap parcelMap;
    private Log logger;

    public Manager() {
        queueOfCustomers = new QueofCustomers();
        parcelMap = new ParcelMap();
        logger = Log.getInstance();
    }

    public void loadCustomersFromCsv(String filePath) throws IOException {
        
    }

    public void loadParcelsFromCsv(String filePath) throws IOException {
        
    }

    public void addNewCustomer(Customer customer) {
        queueOfCustomers.addCustomer(customer);
        logger.writeLog("Added new customer: " + customer.getName());
    }

    public void addNewParcel(Parcel parcel) {
        parcelMap.addParcel(parcel);
        logger.writeLog("Added new parcel: " + parcel.getParcelId());
    }

    public void processNextCustomer() {
        Customer customer = queueOfCustomers.removeCustomer();
        if (customer != null) {
            logger.writeLog("Processing customer: " + customer.getName());
        } else {
            logger.writeLog("No customers in the queue to process.");
        }
    }
}
