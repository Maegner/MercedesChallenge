package pt.sinfo.testDrive.domain;

import java.util.ArrayList;
import java.util.HashMap;

import pt.sinfo.testDrive.exception.TestDriveException;

public class Vehicle {
	private String id;
	private String model;
	private String fuel;
	private String transmission;
	private HashMap<String,ArrayList<Integer>> availability;
	
	public Vehicle(String id, String model,String fuel,String transmission,HashMap<String,ArrayList<Integer>> availability) {
		
		if(id == null || id.trim().equals("") 
		|| model == null || model.trim().equals("")
		|| fuel == null || fuel.trim().equals("")
		|| transmission == null || transmission.trim().equals("") || availability == null) {
			throw new TestDriveException();
		}
		
		this.id = id;
		this.model = model;
		this.fuel = fuel;
		this.transmission = transmission;
		this.availability = availability;
	}
	

	public String getId() {
		return id;
	}
	public String getModel() {
		return model;
	}
	public String getFuel() {
		return fuel;
	}
	public String getTransmission() {
		return transmission;
	}
	public HashMap<String,ArrayList<Integer>> getAvailability() {
		return availability;
	}
	
}
