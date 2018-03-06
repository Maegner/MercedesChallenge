package pt.sinfo.testDrive.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import pt.sinfo.testDrive.exception.BookingNotFoundException;
import pt.sinfo.testDrive.exception.TestDriveException;
import pt.sinfo.testDrive.exception.VehicleNotFoundException;
import pt.sinfo.testDrive.exception.VehicleUnavailableException;

public class Root {
	
	private static Root singleton;
	private HashMap<String,Dealer> dealers;  //DealerId -> Dealer
	private HashMap<String,ArrayList<Booking>> bookings; //Exterior hash, hashed by vehicleID, 
													//interior hash hashed by booking date
	private Root() {
		this.dealers = new HashMap<String,Dealer>();
		this.bookings = new HashMap<String,ArrayList<Booking>>();
	}
	
	private boolean verifyString(String s) {
		return s == null || s.trim().equals("");
	}
	
	public void addNewDealer(Dealer d) {
		this.dealers.put(d.getId(), d);
	}
	
	public void addNewBooking(Booking b) {
		if(this.bookings.containsKey(b.getVehicleId())) {
			this.bookings.get(b.getVehicleId()).add(b);
		}
		else {
			ArrayList<Booking> dateBooking = new ArrayList<Booking>();
			dateBooking.add(b);
			this.bookings.put(b.getVehicleId(), dateBooking);
		}
	}
	
	public static Root getReference() {
		if(singleton == null) {
			singleton = new Root();
		}
		return singleton;
	}
	
	public void setDealers(HashMap<String,Dealer> dealers) {
		this.dealers = dealers;
	}
	public void setBookings(HashMap<String,ArrayList<Booking>> bookings) {
		this.bookings = bookings;
	}
	public HashMap<String,ArrayList<Booking>> getBookings(){
		return this.bookings;
	}
	public HashMap<String,Dealer> getDealers(){
		return this.dealers;
	}

	public boolean isAvailable(String vehicleID,DateTime date) {
		
		if(verifyString(vehicleID) || date == null || date.isBeforeNow()) {
			throw new TestDriveException();
		}
		
		ArrayList<Booking> bookingsForVehicle = bookings.get(vehicleID);
		if(bookingsForVehicle==null) {
			if(this.vehicleById(vehicleID).equals(new ArrayList<Vehicle>())) {
				throw new VehicleNotFoundException();
			}else {
				bookingsForVehicle = new ArrayList<Booking>();
			}
		}
		Booking booked = null;
		for(Booking b : bookingsForVehicle) {
			if(b.getPickupDate().equals(date)) {
				booked = b;
				break;
			}
		}
		
		if(booked==null||(booked!=null&&booked.getCancelledAt()!=null)) {
			return true;
		}else {
			return false;
		}
	}
	
	public List<Vehicle> searchVehicle(String model,String fuel,String transmission, String dealerId ){
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		for(Dealer dealer : dealers.values()) {
			if(dealer.getId()!= dealerId && dealerId!="") {
				continue;
			}
			List<Vehicle> found = dealer.getVehicles().stream()
			.filter( v -> (v.getModel().equals(model)||model.equals(""))
					&& (v.getFuel().equals(fuel)||fuel.equals(""))
					&& (v.getTransmission().equals(transmission)||transmission.equals(""))).collect(Collectors.toList());
			vehicles.addAll(found);
		}
		return vehicles;
	}
	
	public List<Vehicle> vehicleByModel(String model){
		if(verifyString(model)) {
			throw new TestDriveException();
		}
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		for(Dealer dealer : dealers.values()) {
			List<Vehicle> found = dealer.getVehicles().stream()
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
			List<Vehicle> found = dealer.getVehicles().stream()
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
			List<Vehicle> found = dealer.getVehicles().stream()
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
			List<Vehicle> found = dealer.getVehicles().stream()
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
			vehicles = dealer.getVehicles();
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
		Vehicle vehicle = dealer.getVehicles().stream()
				.filter(v -> v.getId().equals(book.getVehicleId()))
				.findFirst().orElse(null);
		
		if(vehicle==null) {
			throw new TestDriveException();
		}
		if(!vehicle.checkAvailability(book.getPickupDate())) {
			throw new VehicleUnavailableException();
		}
		ArrayList<Booking> currentBookings = this.bookings.get(book.getVehicleId());
		if(currentBookings==null) {
			currentBookings = new ArrayList<Booking>();
		}
		currentBookings.add(book);
		this.bookings.put(book.getVehicleId(), currentBookings);
	}
	

	public void cancelBooking(String bookingId,String cancelReason) {
		if(verifyString(bookingId)||verifyString(cancelReason)) {
			throw new TestDriveException();
		}
		Booking bookingToCancel = null;
		outerSearch:
		for(ArrayList<Booking> booked : this.bookings.values()) {
			for(Booking b : booked) {
				if(b.getId().equals(bookingId)) {
					bookingToCancel = b;
					b.cancel(cancelReason);
					break outerSearch;
				}
			}
		}
		if(bookingToCancel==null) {
			throw new BookingNotFoundException();
		}
	}
	
	public Vehicle findVehicleWithSpecs(Dealer dealer,String model,String fuel,String transmission) {
		if(model==null|| transmission==null||fuel==null) {
			throw new TestDriveException();
		}
		return dealer.getVehicles().stream()
				.filter(v -> (v.getModel().equals(model)||model.equals(""))
						&& (v.getFuel().equals(fuel)||fuel.equals(""))
						&& (v.getTransmission().equals(transmission)||transmission.equals("")))
				.findFirst().orElse(null);
	}
	
	public Dealer closestDealer(String model,String fuel,String transmission,Coordinate position) {
		if(position==null) {
			throw new TestDriveException();
		}
		Dealer result = null;
		float lat = position.getLatitude();
		float longi = position.getLongitude();
		double minimalDistance = -1;
		for(Dealer dealer : dealers.values()) {
			Vehicle vehicle = findVehicleWithSpecs(dealer, model, fuel, transmission);
			if(vehicle!=null) {
				double distanceToDealer = dealer.getDistance(longi, lat);
				if(minimalDistance==-1 || minimalDistance> distanceToDealer) {
					minimalDistance = distanceToDealer;
					result = dealer;
				}
			}
		}
		return result;
	}
	
	public TreeSet<Dealer> createDealerTreeSet(Coordinate position) {
		return new TreeSet<Dealer>(new Comparator<Dealer>() {
			@Override
			public int compare(Dealer arg0, Dealer arg1) {
				float lat=position.getLatitude();
				float longi = position.getLongitude();
				double distance0 = arg0.getDistance(longi, lat);
				double distance1 = arg1.getDistance(longi, lat);
				if (distance0==distance1) {return 0;}
				else if(distance0>distance1) {return 1;}
				else {return -1;}
			}
		});
		
	}
	
	public ArrayList<Dealer> dealersWithSpecdVehicles(String model,String fuel,String transmission,Coordinate position) { 
		TreeSet<Dealer> dealers = createDealerTreeSet(position);
		for(Dealer dealer: this.dealers.values()) {
			Vehicle vehicle = findVehicleWithSpecs(dealer, model, fuel, transmission);
			if (vehicle!=null) {
				dealers.add(dealer);
			}
		}		
		return new ArrayList<Dealer>(dealers);
	}
	
	public ArrayList<Dealer> dealersInPoligon(String model,String fuel,String transmission,Coordinate topRight,Coordinate bottomLeft){
		ArrayList<Dealer> result = new ArrayList<Dealer>();
		for(Dealer dealer: this.dealers.values()) {
			Vehicle vehicle = findVehicleWithSpecs(dealer, model, fuel, transmission);
			if (vehicle!=null && dealer.isInPoligon(topRight, bottomLeft) ) {
				result.add(dealer);
			}
		}
		return result;
	}
	
}