package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Manager {
	private QueofCustomers queueOfCustomers;
    private ParcelMap parcelMap;
    private Log logger;
    private List<Customer> customers = new ArrayList<>();
    private List<Parcel> parcels;
    
    public Manager() {
        this.customers = new ArrayList<>();
        this.parcels = new ArrayList<>();
        this.queueOfCustomers = new QueofCustomers();
        this.parcelMap = new ParcelMap();
//        this.logger = new Log(); 
    }

    public QueofCustomers getQueueOfCustomers() {
        return queueOfCustomers;
    }
    
    public void loadCustomersFromExcel(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header row.

            String name = row.getCell(0).getStringCellValue();
            String parcelId = row.getCell(1).getStringCellValue();
            customers.add(new Customer(name, parcelId));
        }

        workbook.close();
        file.close();
    }
    
    public void loadCustomersFromCsv(String filePath) throws IOException {
        List<Customer> customers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); 
                if (values.length >= 2) {
                    String name = values[0].trim();
                    String parcelId = values[1].trim();
                    Customer customer = new Customer(name, parcelId);
                    customers.add(customer);
                    
                }
            }
        }

        this.customers = customers;
        queueOfCustomers.bulkAddCustomers(customers);
    }

    public void loadParcelsFromCsv(String filePath) throws IOException {
        
    }
    public List<Customer> getCustomers() {
    	return customers;
    }
    
    public List<Parcel> getParcels() {
        return parcels;
    }

    public void addNewCustomer(Customer customer) {
        customers.add(customer);
        queueOfCustomers.addCustomer(customer);
        logger.writeLog("Added new customer: " + customer.getName());
    }

    public void addNewParcel(Parcel parcel) {
        parcels.add(parcel);
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
