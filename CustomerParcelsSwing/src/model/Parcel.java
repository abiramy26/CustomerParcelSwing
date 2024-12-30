package model;

public class Parcel {
	    private String parcelId;
	    private double weight;
	    private int height;
	    private int width;
	    private int length;
	    private int priority;

	    public Parcel(String parcelId, double weight, int height, int width, int length, int priority) {
	        this.parcelId = parcelId;
	        this.weight = weight;
	        this.height = height;
	        this.width = width;
	        this.length = length;
	        this.priority = priority;
	    }

	    
	
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



		public int getHeight() {
			return height;
		}



		public void setHeight(int height) {
			this.height = height;
		}



		public int getWidth() {
			return width;
		}



		public void setWidth(int width) {
			this.width = width;
		}



		public int getLength() {
			return length;
		}



		public void setLength(int length) {
			this.length = length;
		}



		public int getPriority() {
			return priority;
		}



		public void setPriority(int priority) {
			this.priority = priority;
		}



	@Override
    public String toString() {
        return "Parcel{parcelId='" + parcelId + "', weight=" + weight +
                ", dimensions=[" + height + ", " + width + ", " + length +
                "], type='" + priority + "'}";
    }
	
}
