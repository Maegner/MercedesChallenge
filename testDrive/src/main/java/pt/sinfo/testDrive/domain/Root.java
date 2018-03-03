package pt.sinfo.testDrive.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import pt.sinfo.testDrive.exception.TestDriveException;
import pt.sinfo.testDrive.exception.VehicleNotFoundException;

public class Root {
	HashMap<String,Dealer> dealers;
	HashMap<String,HashMap<DateTime,Booking>> bookings; //Exterior hash, hashed by vehicleID, 
													//interior hash hashed by booking date
	private boolean verifyString(String s) {
		return s == null || s.trim().equals("");
	}
	
	public Root(HashMap<String,Dealer> dealers,HashMap<String,HashMap<DateTime,Booking>> bookings) {
		this.dealers = dealers;
		this.bookings = bookings;
	}

	public boolean isAvailable(String vehicleID,DateTime date) {
		
		if(verifyString(vehicleID) || date == null || date.isBeforeNow()) {
			throw new TestDriveException();
		}
		
		HashMap<DateTime,Booking> bookingsForVehicle = bookings.get(vehicleID);
		if(bookingsForVehicle==null) {
			throw new VehicleNotFoundException();
		}else {
			Booking booked = bookingsForVehicle.get(date);
			if(booked==null) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	public List<Vehicle> vehicleByModel(String model){
		if(verifyString(model)) {
			throw new TestDriveException();
		}
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		for(Dealer dealer : dealers.values()) {
			List<Vehicle> found = dealer.getVehicles()
			.filter( v -> v.getModel().equals(model)).collect(Collectors.toList());
			vehicles.addAll(found);
		}
		return vehicles;
	}
	
	public List<Vehicle> vehicleByFuel(String fuel){
		if(verifyString(fuel)) {
			throw new TestDriveException();
		}
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		for(Dealer dealer : dealers.values()) {
			List<Vehicle> found = dealer.getVehicles()
			.filter( v -> v.getFuel().equals(fuel)).collect(Collectors.toList());
			vehicles.addAll(found);
		}
		return vehicles;
	}
	
	public List<Vehicle> vehicleByTransmission(String transmission){
		if(verifyString(transmission)) {
			throw new TestDriveException();
		}
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		for(Dealer dealer : dealers.values()) {
			List<Vehicle> found = dealer.getVehicles()
			.filter( v -> v.getTransmission().equals(transmission)).collect(Collectors.toList());
			vehicles.addAll(found);
		}
		return vehicles;
	}
	
	public List<Vehicle> vehicleByDealer(String dID){
		if(verifyString(dID)) {
			throw new TestDriveException();
		}
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Dealer dealer = dealers.get(dID);
		if(dealer!=null) {
			vehicles = dealer.getVehicles().collect(Collectors.toList());
		}
		return vehicles;
	}
}