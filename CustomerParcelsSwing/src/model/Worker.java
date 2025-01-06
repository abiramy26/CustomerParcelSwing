package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import javax.swing.table.DefaultTableModel;

public class Worker {
    private Queue<Customer> customerQueue;
    private Log logger;
    private List<Parcel> parcels = new ArrayList<>(); 
    
    public Worker() {
        this.logger = Log.getInstance(); // Singleton logger
        parcels = new ArrayList<>();
    }

    public Worker(Queue<Customer> customerQueue) {
        this.customerQueue = customerQueue;
        this.logger = Log.getInstance(); // Singleton logger
    }

    public void processNextCustomer() {
        if (customerQueue.isEmpty()) {
            System.out.println("No customers in the queue.");
            return;
        }

        Customer customer = customerQueue.poll();

        System.out.println("Processing customer: " + customer.getName());
        logger.writeLog("Processing customer: " + customer.getName());

        List<Parcel> customerParcels = customer.getParcels();
        parcels.addAll(customerParcels);  // Add parcels to Worker’s list

        for (Parcel parcel : customerParcels) {
            double fee = calculateFee(parcel);
            parcel.setProcessed(true);

            System.out.println("Parcel ID: " + parcel.getParcelId() + " processed. Fee: " + fee);
            logger.writeLog("Parcel ID: " + parcel.getParcelId() + " processed. Fee: " + fee);
        }

        System.out.println("Customer " + customer.getName() + " processing completed.");
        logger.writeLog("Customer " + customer.getName() + " processing completed.");
    }

    public double calculateFee(Parcel parcel) {
        double baseFee = 50.0; // Base fee
        double weightFee = parcel.getWeight() * 10; // Fee per kg
        double sizeFee = (parcel.getHeight() * parcel.getWidth() * parcel.getLength()) / 1000.0; // Volume fee

        double totalFee = baseFee + weightFee + sizeFee;

        if (parcel.getPriority() > 0) {
            totalFee += 20 * parcel.getPriority();
        }

        return totalFee;
    }

    public String processCustomer(Parcel parcel, DefaultTableModel model, int selectedRow, String filePath) {
        if (parcel != null) {
            // Check if the parcel is already processed
            if (parcel.isProcessed()) {
                // Parcel is already processed, return a message
                System.out.println("Parcel is already processed.");
                logger.writeLog("Parcel ID: " + parcel.getParcelId() + " is already processed.");
                return "Parcel is already processed.";
            } else {
                // Calculate the fee
                double fee = calculateFee(parcel);
                parcel.setFee(fee); // Set the fee in the parcel
                parcel.setProcessed(true); // Mark the parcel as processed

                // Format the fee to two decimal places
                String formattedFee = String.format("%.2f", fee);

                // Log the action
                System.out.println("Parcel processed. Fee: " + formattedFee);
                logger.writeLog("Parcel ID: " + parcel.getParcelId() + " processed. Fee: " + formattedFee);

                // Update the table in the view (worker is now responsible for this)
                model.setValueAt(formattedFee, selectedRow, 6); // Update Fee column (6th column)
                model.setValueAt("Yes", selectedRow, 7); // Update Processed column (7th column)

                // Save the parcels to CSV after processing
                try {
                    saveParcelsToCsv(filePath);
                } catch (IOException e) {
                    System.err.println("Failed to save parcels to CSV.");
                }

                // Return success message
                return "Parcel processed successfully.";
            }
        } else {
            System.out.println("No parcel to process.");
            return "No parcel to process.";
        }
    }

    public List<Parcel> getParcels() {
        return parcels;
    }
    
    public void saveParcelsToCsv(String filePath) throws IOException {
        if (parcels == null || parcels.isEmpty()) {
            System.out.println("No parcels to save.");
            return;  // Avoid proceeding if the list is null or empty
        }

        System.out.println("Saving parcels to file: " + filePath);
        System.out.println("Number of parcels to save: " + parcels.size());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write header
            writer.write("ParcelId,Weight,Height,Width,Length,Priority,Processed,Fee");
            writer.newLine();

            // Write parcel data
            for (Parcel parcel : parcels) {
                writer.write(String.format("%s,%.2f,%d,%d,%d,%d,%s,%.2f",
                        parcel.getParcelId(),
                        parcel.getWeight(),
                        parcel.getHeight(),
                        parcel.getWidth(),
                        parcel.getLength(),
                        parcel.getPriority(),
                        parcel.isProcessed() ? "Yes" : "No",
                        parcel.getFee()));  // Include the fee here
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            e.printStackTrace();
            throw e; // Re-throw for upper-level handling
        }

        System.out.println("Save completed successfully.");
    }


}
