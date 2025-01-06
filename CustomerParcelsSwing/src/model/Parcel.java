package model;

public class Parcel {
	    private String parcelId;
	    private double weight;
	    private int height;
	    private int width;
	    private int length;
	    private int priority;
	    private boolean processed;
	    private double fee;
	    
	    public Parcel(String parcelId, double weight, int height, int width, int length, int priority) {
	        this.parcelId = parcelId;
	        this.weight = weight;
	        this.height = height;
	        this.width = width;
	        this.length = length;
	        this.priority = priority;
	        this.processed = false;
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
		
		public boolean isProcessed() {
	        return processed;
	    }

	    public void setProcessed(boolean processed) {
	        this.processed = processed;
	    }

	    
	    public double getFee() {
			return fee;
		}

		public void setFee(double fee) {
			this.fee = fee;
		}

		@Override
	    public String toString() {
	        return "Parcel{" +
	                "parcelId='" + parcelId + '\'' +
	                ", weight=" + weight +
	                ", height=" + height +
	                ", width=" + width +
	                ", length=" + length +
	                ", priority=" + priority +
	                ", processed=" + processed +
	                ", Fee=" + fee +
	                '}';
	    }
	
}
