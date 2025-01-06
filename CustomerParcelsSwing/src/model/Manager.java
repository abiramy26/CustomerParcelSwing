package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
    private Queue<Customer> queueOfCustomers2 = new LinkedList<>();
    
    public Manager() {
        this.customers = new ArrayList<>();
        this.parcels = new ArrayList<>();
        this.queueOfCustomers = new QueofCustomers();
        this.parcelMap = new ParcelMap();
        this.logger = Log.getInstance(); 
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
        // Clear existing data to avoid duplication
        customers.clear();
        queueOfCustomers.clearQueue();
        List<Customer> customersFromCsv = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 2) {
                    String name = values[0].trim();
                    String parcelId = values[1].trim();
                    Customer customer = new Customer(name, parcelId);
                    customersFromCsv.add(customer);
                }
            }
        }

        this.customers = customersFromCsv;
        queueOfCustomers.bulkAddCustomers(customersFromCsv);
    }

    public void loadParcelsFromCsv(String filePath) throws IOException {
        List<Parcel> parcels = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 6) {
                    String parcelId = values[0].trim();
                    double weight = Double.parseDouble(values[1].trim());
                    int height = Integer.parseInt(values[2].trim());
                    int width = Integer.parseInt(values[3].trim());
                    int length = Integer.parseInt(values[4].trim());
                    int priority = Integer.parseInt(values[5].trim());
                    Parcel parcel = new Parcel(parcelId, weight, height, width, length, priority);

                    parcels.add(parcel); // Add to the parcels list
                    parcelMap.addParcel(parcel); // Add to the parcel map
                }
            }
        }

        this.parcels = parcels;
    }

    public void saveParcelsToCsv(String filePath) throws IOException {
        System.out.println("Saving parcels to file: " + filePath);
        System.out.println("Number of parcels to save: " + parcels.size());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write header
            writer.write("ParcelId,Weight,Height,Width,Length,Priority");
            writer.newLine();

            // Write parcel data
            for (Parcel parcel : parcels) {
                writer.write(String.format("%s,%.2f,%d,%d,%d,%d",
                        parcel.getParcelId(),
                        parcel.getWeight(),
                        parcel.getHeight(),
                        parcel.getWidth(),
                        parcel.getLength(),
                        parcel.getPriority()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            e.printStackTrace();
            throw e; // Re-throw for upper-level handling
        }

        System.out.println("Save completed successfully.");
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

    private Log getLogger() {
        if (logger == null) {
            logger = Log.getInstance();
        }
        return logger;
    }
    
    public void addNewParcel(Parcel parcel) {
        parcels.add(parcel); 
        parcelMap.addParcel(parcel);
        getLogger().writeLog("Added new parcel: " + parcel.getParcelId());
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
