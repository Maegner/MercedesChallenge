package pt.sinfo.testDrive.domain;

import java.util.HashSet;
import java.util.stream.Stream;

import pt.sinfo.testDrive.exception.TestDriveException;

public class Dealer {
	
	private String id;
	private String name;
	private Integer latitude;
	private Integer longitude;
	private Stream<Vehicle> vehicles;
	private HashSet<String> closed;
	
	public boolean verifyString(String s) {
		return s == null || s.trim().equals("");
	}
	
	public boolean invalidArguments(String id, String name,Integer latitude,Integer longitude,Stream<Vehicle> vehicles) {
		return verifyString(id) || verifyString(name) || latitude==null 
				|| longitude==null || vehicles == null;
		
	}
	
	public Dealer(String id, String name,Integer latitude,Integer longitude,Stream<Vehicle> vehicles,HashSet<String> closed) {
		
		if(invalidArguments(id, name, latitude, longitude, vehicles)) {
			throw new TestDriveException();
		}
		
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.vehicles = vehicles;
		this.closed = closed;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Integer getLatitude() {
		return latitude;
	}
	public Integer getLongitude() {
		return longitude;
	}
	public Stream<Vehicle> getVehicles() {
		return vehicles;
	}
	public HashSet<String> getClosed() {
		return closed;
	}
	
	public double getDistance(int longi,int lat) {
		return Math.sqrt(Math.pow((this.longitude-longi), 2) + Math.pow((this.latitude-lat), 2));
	}
	

}
