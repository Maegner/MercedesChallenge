package pt.sinfo.testDrive.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Stream;

import pt.sinfo.testDrive.exception.TestDriveException;

public class Dealer {
	
	private String id;
	private String name;
	private Coordinate coordinate;
	private ArrayList<Vehicle> vehicles;
	private HashSet<String> closed;
	
	private boolean verifyString(String s) {
		return s == null || s.trim().equals("");
	}
	
	public boolean invalidArguments(String id, String name,Integer latitude,Integer longitude,ArrayList<Vehicle> vehicles2) {
		return verifyString(id) || verifyString(name) || latitude==null 
				|| longitude==null || vehicles2 == null;
		
	}
	
	public Dealer(String id, String name,Integer latitude,Integer longitude,ArrayList<Vehicle> vehicles,HashSet<String> closed) {
		
		if(invalidArguments(id, name, latitude, longitude, vehicles)) {
			throw new TestDriveException();
		}
		
		this.id = id;
		this.name = name;
		this.coordinate = new Coordinate(longitude, latitude);
		this.vehicles = vehicles;
		this.closed = closed;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Coordinate getCoordinate() {
		return coordinate;
	}
	public void setPosition(int latitude,int longitude) {
		this.coordinate.setLatitude(latitude);
		this.coordinate.setLongitude(longitude);
	}
	public Stream<Vehicle> getVehicles() {
		return vehicles.stream();
	}
	public HashSet<String> getClosed() {
		return closed;
	}
	
	public double getDistance(int longi,int lat) {
		return Math.sqrt(Math.pow((this.coordinate.getLongitude()-longi), 2) + Math.pow((this.coordinate.getLatitude()-lat), 2));
	}
	
	public boolean isInPoligon(Coordinate topRight, Coordinate bottomLeft) {
		return ((bottomLeft.getLongitude()<=this.coordinate.getLongitude())
				&&(this.coordinate.getLongitude()<=topRight.getLongitude())
				&&(bottomLeft.getLatitude()<=this.coordinate.getLatitude())
				&&(this.coordinate.getLatitude()<=topRight.getLatitude()));
	}

}
