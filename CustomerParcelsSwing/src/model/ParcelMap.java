package model;

import java.util.HashMap;
import java.util.Map;

public class ParcelMap {
    private Map<String, Parcel> parcelMap;

    public ParcelMap() {
        this.parcelMap = new HashMap<>();
    }

    // Add a parcel to the map
    public void addParcel(Parcel parcel) {
        parcelMap.put(parcel.getParcelId(), parcel);
    }

    // Get a parcel by its ID
    public Parcel getParcelById(String parcelId) {
        return parcelMap.get(parcelId);
    }

    // Remove a parcel by its ID
    public void removeParcel(String parcelId) {
        parcelMap.remove(parcelId);
    }

    // Check if a parcel exists by its ID
    public boolean containsParcel(String parcelId) {
        return parcelMap.containsKey(parcelId);
    }

    // Get the total number of parcels
    public int getParcelCount() {
        return parcelMap.size();
    }

    // Clear all parcels from the map
    public void clearParcels() {
        parcelMap.clear();
    }

    // Return all parcels as a collection
    public Map<String, Parcel> getAllParcels() {
        return parcelMap;
    }
}
