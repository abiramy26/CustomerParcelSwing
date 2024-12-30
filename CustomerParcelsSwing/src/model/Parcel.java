package model;

public class Parcel {
	
	private String parcelId;
    private double weight;
    private int dimension1;
    private int dimension2;
    private int dimension3;
    private String type;
    
    public String getParcelId() {
		return parcelId;
	}
	public void setParcelId(String parcelId) {
		this.parcelId = parcelId;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getDimension1() {
		return dimension1;
	}
	public void setDimension1(int dimension1) {
		this.dimension1 = dimension1;
	}
	public int getDimension2() {
		return dimension2;
	}
	public void setDimension2(int dimension2) {
		this.dimension2 = dimension2;
	}
	public int getDimension3() {
		return dimension3;
	}
	public void setDimension3(int dimension3) {
		this.dimension3 = dimension3;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
