package model;

public class Customer {

	private String name;
    private String parcelId;
    
    public Customer(String name, String parcelId) {
        this.name = name;
        this.parcelId = parcelId;
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
