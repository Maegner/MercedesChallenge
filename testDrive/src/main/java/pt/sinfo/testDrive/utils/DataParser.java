package pt.sinfo.testDrive.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.ISODateTimeFormat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import pt.sinfo.testDrive.domain.Booking;
import pt.sinfo.testDrive.domain.Dealer;
import pt.sinfo.testDrive.domain.Root;
import pt.sinfo.testDrive.domain.Vehicle;
import pt.sinfo.testDrive.exception.TestDriveException;

public class DataParser {
	private static DataParser singleton;
	
	private DataParser() {
		
	}
	
	
	private HashMap<String,ArrayList<Integer>> getDay(JSONObject object,HashMap<String,ArrayList<Integer>> availability,String day){
		if(object.containsKey(day)) {
			JSONArray jsonHours = (JSONArray) object.get(day);
			ArrayList<Integer> hours = new ArrayList<Integer>();
			for(Object obj : jsonHours) {
				int hour = Integer.parseInt(obj.toString());
				hours.add(hour);
			}
			availability.put(day, hours);
		}
		return availability;
	}
	
	private HashMap<String,ArrayList<Integer>> parseAvailability(JSONObject object){
		HashMap<String,ArrayList<Integer>> availability = new HashMap<String,ArrayList<Integer>>();
		availability = getDay(object,availability, "monday");
		availability = getDay(object,availability, "tuesday");
		availability = getDay(object,availability, "wednesday");
		availability = getDay(object,availability, "thursday");
		availability = getDay(object,availability, "friday");
		availability = getDay(object,availability, "saturday");
		availability = getDay(object,availability, "sunday");
		return availability;
	}
	
	private Vehicle parseVehicle(JSONObject object) {
		String id = object.get("id").toString();
		String model = object.get("model").toString();
		String fuel = object.get("fuel").toString();
		String transmission = object.get("transmission").toString();
		HashMap<String,ArrayList<Integer>> availability = parseAvailability((JSONObject)object.get("availability"));
		return new Vehicle(id, model, fuel, transmission, availability);
	}
	
	private Dealer parseDealer(JSONObject object) {
		String id = object.get("id").toString();
		String name = object.get("name").toString();
		float latitude = Float.parseFloat(object.get("latitude").toString());
		float longitude = Float.parseFloat(object.get("longitude").toString());
		JSONArray vehiclesArray = (JSONArray) object.get("vehicles");
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		for(Object obj : vehiclesArray) {
			JSONObject vehicleObj = (JSONObject) obj;
			vehicles.add(parseVehicle(vehicleObj));
		}
		JSONArray closedObject = (JSONArray) object.get("closed");
		HashSet<String> closed = new HashSet<String>();
		for(Object obj: closedObject) {
			String day = obj.toString();
			closed.add(day);
		}
		return new Dealer(id, name, latitude, longitude, vehicles, closed);
	}
	
	private DateTime parseDate(String dateString) {
		DateTime date = null;
		try {
			date = DateTime.parse(dateString,ISODateTimeFormat.dateTimeParser());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	private Booking parseBooking(JSONObject object) {
		String id = object.get("id").toString();
		String firstName = object.get("firstName").toString();
		String lastName = object.get("lastName").toString();
		String vehicleId = object.get("vehicleId").toString();
		String pickupDateString = object.get("pickupDate").toString();
		String createdDateString = object.get("createdAt").toString();
		DateTime pickupDate = parseDate(pickupDateString);
		DateTime createdAt = parseDate(createdDateString);
		Booking booking = new Booking(id, vehicleId, firstName, lastName, pickupDate,createdAt);
		if(object.containsKey("cancelledAt") && object.containsKey("cancelledReason")){
			DateTime cancelDate = parseDate(object.get("cancelledAt").toString());
			String reason = object.get("cancelledReason").toString();
			booking.cancel(reason,cancelDate);
		}
		return booking;
	}
	
	public static void populate() {
		if(singleton==null) {
			singleton = new DataParser();
		}
		JSONObject object =null;
		Root root = Root.getReference();
		try {
			JSONParser parser = new JSONParser();
			String path = "resources/dataset.json";
			object = (JSONObject) parser.parse(new FileReader(path));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		JSONArray dealersArray = (JSONArray) object.get("dealers");
		JSONArray bookingsArray = (JSONArray) object.get("bookings");
		
		for(Object obj : dealersArray) {
			Dealer dealer = singleton.parseDealer((JSONObject) obj);
			root.addNewDealer(dealer);
		}
		for(Object obj:bookingsArray) {
			Booking booking = singleton.parseBooking((JSONObject) obj);
			root.addNewBooking(booking);
		}
		
	}
}
