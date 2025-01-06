package model;

import java.util.List;

public class Customer {

	private String name;
    private String parcelId;
    private List<Parcel> parcels;
    private Parcel parcel;
    
    public List<Parcel> getParcels() {
        return parcels;
    }
    public Customer(String name, String parcelId) {
        this.name = name;
        this.parcelId = parcelId;
    }
    public Parcel getParcel() {
        return parcel;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParcelId() {
		return parcelId;
	}
	public void setParcelId(String parcelId) {
		this.parcelId = parcelId;
	}
    
	@Override
    public String toString() {
        return "Customer{name='" + name + "', parcelId='" + parcelId + "'}";
    }
    
    
}
