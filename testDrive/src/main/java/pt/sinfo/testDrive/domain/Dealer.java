package pt.sinfo.testDrive.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import pt.sinfo.testDrive.exception.TestDriveException;

public class Dealer {
	
	private String id;
	private String name;
	@JsonUnwrapped
	private Coordinate coordinate;
	//@JsonIgnore
	private ArrayList<Vehicle> vehicles;
	private HashSet<String> closed;
	
	private boolean verifyString(String s) {
		return s == null || s.trim().equals("");
	}
	
	public boolean invalidArguments(String id, String name,float latitude,float longitude,ArrayList<Vehicle> vehicles2) {
		return verifyString(id) || verifyString(name) || vehicles2 == null;
		
	}
	
	public Dealer(String id, String name,float latitude,float longitude,ArrayList<Vehicle> vehicles,HashSet<String> closed) {
		
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
	public ArrayList<Vehicle> getVehicles(){
		return vehicles;
	}
	public HashSet<String> getClosed() {
		return closed;
	}
	
	public double getDistance(float longi,float lat) {
		return Math.sqrt(Math.pow((this.coordinate.getLongitude()-longi), 2) + Math.pow((this.coordinate.getLatitude()-lat), 2));
	}
	
	public boolean isInPoligon(Coordinate topRight, Coordinate bottomLeft) {
		return ((bottomLeft.getLongitude()<=this.coordinate.getLongitude())
				&&(this.coordinate.getLongitude()<=topRight.getLongitude())
				&&(bottomLeft.getLatitude()<=this.coordinate.getLatitude())
				&&(this.coordinate.getLatitude()<=topRight.getLatitude()));
	}

}
