package model;

import java.util.HashMap;
import java.util.Map;

//manages parcels using a HashMap, mapping parcel IDs to Parcel objects.
public class ParcelMap {
	private Map<String, Parcel> parcelMap;

    public ParcelMap() {
        parcelMap = new HashMap<>();
    }

    public void addParcel(Parcel parcel) {
        parcelMap.put(parcel.getParcelId(), parcel);
    }

    public Parcel getParcelById(String parcelId) {
        return parcelMap.get(parcelId);
    }

    public boolean containsParcel(String parcelId) {
        return parcelMap.containsKey(parcelId);
    }

    public int size() {
        return parcelMap.size();
    }
}
