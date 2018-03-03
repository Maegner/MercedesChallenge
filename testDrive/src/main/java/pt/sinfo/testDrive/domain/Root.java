package pt.sinfo.testDrive.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import pt.sinfo.testDrive.exception.TestDriveException;
import pt.sinfo.testDrive.exception.VehicleNotFoundException;
import pt.sinfo.testDrive.exception.VehicleUnavailableException;

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
			if(this.vehicleById(vehicleID).equals(new ArrayList<Vehicle>())) {
				throw new VehicleNotFoundException();
			}else {
				bookingsForVehicle = new HashMap<DateTime,Booking>();
			}
		}
		Booking booked = bookingsForVehicle.get(date);
		if(booked==null) {
			return true;
		}else {
			return false;
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
	public List<Vehicle> vehicleById(String id){
		if(verifyString(id)) {
			throw new TestDriveException();
		}
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		for(Dealer dealer : dealers.values()) {
			List<Vehicle> found = dealer.getVehicles()
			.filter( v -> v.getId().equals(id)).collect(Collectors.toList());
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
	
	public void bookVehicle(String dealerId,Booking book) {
		if(dealerId==null || book == null) {
			throw new TestDriveException();
		}
		if(!isAvailable(book.getVehicleId(), book.getPickupDate())) {
			throw new VehicleUnavailableException();
		}
		Dealer dealer = this.dealers.get(dealerId);
		if(dealer==null) {
			throw new TestDriveException();
		}
		Vehicle vehicle = dealer.getVehicles()
				.filter(v -> v.getId().equals(book.getVehicleId()))
				.findFirst().orElse(null);
		
		if(vehicle==null) {
			throw new TestDriveException();
		}
		if(!vehicle.checkAvailability(book.getPickupDate())) {
			throw new VehicleUnavailableException();
		}
		HashMap<DateTime,Booking> currentBookings = this.bookings.get(book.getVehicleId());
		if(currentBookings==null) {
			currentBookings = new HashMap<DateTime,Booking>();
		}
		currentBookings.put(book.getPickupDate(), book);
		this.bookings.put(book.getVehicleId(), currentBookings);
	}
	
	public Dealer closestDealer(String model,String fuel,String transmission,ArrayList<Integer>position) {
		Dealer result = null;
		int lat = position.get(0);
		int longi = position.get(1);
		double minimalDistance = -1; 
		for(Dealer dealer : dealers.values()) {
			Vehicle vehicle = dealer.getVehicles()
					.filter(v -> v.getModel().equals(model)
							&& v.getFuel().equals(fuel)
							&& v.getTransmission().equals(transmission))
					.findFirst().orElse(null);
			if(vehicle!=null) {
				double distanceToDealer = dealer.getDistance(longi, lat);
				if(minimalDistance==-1 || minimalDistance> distanceToDealer) {
					result = dealer;
				}
			}
		}
		return result;
	}
}