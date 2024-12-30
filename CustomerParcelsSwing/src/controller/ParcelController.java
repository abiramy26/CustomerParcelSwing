package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import model.Parcel;

public class ParcelController {
	private List<Parcel> parcels = new ArrayList<>(); 
	public void loadParcelsFromCsv(String filepath) throws Exception{
		List<Parcel> parcels = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))){
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if(values.length >= 6) {
					String parcelId = values[0].trim();
					double weight = Double.parseDouble(values[1].trim());
				    int height =  Integer.parseInt(values[2].trim());
				    int width = Integer.parseInt(values[3].trim());
				    int length = Integer.parseInt(values[4].trim());
				    int priority = Integer.parseInt(values[5].trim());
				    parcels.add(new Parcel(parcelId, weight, height, width, length, priority));
				}
			}
		}
		this.parcels = parcels;
	}
	
	public List<Parcel> getParcels(){
		return parcels;
	}
}

