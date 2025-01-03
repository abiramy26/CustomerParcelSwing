package controller;

import java.io.IOException;
import java.util.List;

import model.Manager;
import model.Parcel;

public class ParcelController {
    private Manager manager;
    private String filePath = "C:\\Users\\User\\Downloads\\java course work\\java course work\\Parcels.csv";
    public ParcelController(Manager manager) {
        this.manager = manager;
    }

    public synchronized void loadParcelsFromCsv() {
        try {
            manager.loadParcelsFromCsv(filePath);
            System.out.println("Parcels loaded successfully from: " + filePath);
        } catch (IOException e) {
            System.err.println("Error loading parcels from CSV: " + e.getMessage());
        }
    }

    // Save parcels to the default CSV file
    public synchronized void saveParcelsToCsv() {
        try {
            manager.saveParcelsToCsv(filePath);
            System.out.println("Parcels saved successfully to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving parcels to CSV: " + e.getMessage());
        }
    }

    // Add a new parcel
    public synchronized void addParcel(Parcel parcel,String filePath) {
        manager.addNewParcel(parcel);
        System.out.println("Parcel added successfully: " + parcel.getParcelId());
        saveParcelsToCsv();
    }

    // Retrieve all parcels
    public List<Parcel> getAllParcels() {
        return manager.getParcels();
    }

    // Get parcel by ID
    public Parcel getParcelById(String parcelId) {
        for (Parcel parcel : manager.getParcels()) {
            if (parcel.getParcelId().equals(parcelId)) {
                return parcel;
            }
        }
        System.out.println("Parcel not found: " + parcelId);
        return null;
    }

    // Remove a parcel by ID
    public synchronized void removeParcelById(String parcelId,String filePath) {
        Parcel parcelToRemove = null;

        for (Parcel parcel : manager.getParcels()) {
            if (parcel.getParcelId().equals(parcelId)) {
                parcelToRemove = parcel;
                break;
            }
        }

        if (parcelToRemove != null) {
            manager.getParcels().remove(parcelToRemove);
            System.out.println("Parcel removed successfully: " + parcelId);
            saveParcelsToCsv();
        } else {
            System.out.println("Parcel not found: " + parcelId);
        }
    }
}
