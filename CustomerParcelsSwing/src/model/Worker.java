package model;

public class Worker {
	private String workerId;
    private String name;

    public Worker(String workerId, String name) {
        this.workerId = workerId;
        this.name = name;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void processParcel(Parcel parcel) {
        // Add parcel processing logic
        System.out.println("Processing parcel: " + parcel.getParcelId());
    }
}
