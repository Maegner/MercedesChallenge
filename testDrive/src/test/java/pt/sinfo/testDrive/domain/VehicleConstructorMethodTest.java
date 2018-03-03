package pt.sinfo.testDrive.domain;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.sinfo.testDrive.exception.TestDriveException;


public class VehicleConstructorMethodTest {
	
	private String id;
	private String model;
	private String fuel;
	private String transmission;
	private HashMap<String,ArrayList<Integer>> availability;
	
	
	@Before
	public void setUp(){
		this.id = "3928f780-295b-4dd0-8cc9-28c0738398d9";
		this.model = "AMG";
		this.fuel = "ELECTRIC";
		this.transmission = "AUTO";
		this.availability = new HashMap<String,ArrayList<Integer>>();
		ArrayList<Integer> dayOne = new ArrayList<Integer>();
		ArrayList<Integer> dayTwo = new ArrayList<Integer>();
		dayOne.add(1000);dayOne.add(1030);
		dayTwo.add(1000);dayTwo.add(1030);
		availability.put("thursday",dayOne);
		availability.put("Monday", dayTwo);
	}
	
	@Test
	public void success() {
		
		
		Vehicle testSubject = new Vehicle(id,model,fuel,transmission,availability);
		Assert.assertEquals(id,testSubject.getId());
		Assert.assertEquals(model,testSubject.getModel());
		Assert.assertEquals(fuel,testSubject.getFuel());
		Assert.assertEquals(transmission,testSubject.getTransmission());
		Assert.assertEquals(availability,testSubject.getAvailability());
	}
	//TESTING FOR NULL ARGUMENTS
	@Test(expected = TestDriveException.class)
	public void nullId() {
		new Vehicle(null, this.model, this.fuel, this.transmission, this.availability);
	}
	@Test(expected = TestDriveException.class)
	public void nullModel() {
		new Vehicle(this.id, null, this.fuel, this.transmission, this.availability);
	}
	@Test(expected = TestDriveException.class)
	public void nullFuel() {
		new Vehicle(this.id, this.model, null, this.transmission, this.availability);
	}
	@Test(expected = TestDriveException.class)
	public void nullTransmission() {
		new Vehicle(this.id, this.model, this.fuel, null, this.availability);
	}
	@Test(expected = TestDriveException.class)
	public void nullAvailability() {
		new Vehicle(this.id, this.model, this.fuel, this.transmission, null);
	}
	
	//TESTING FOR EMPTY ARGUMENTS
	@Test(expected = TestDriveException.class)
	public void emptyId() {
		new Vehicle("", this.model, this.fuel, this.transmission, this.availability);
	}
	@Test(expected = TestDriveException.class)
	public void emptyModel() {
		new Vehicle(this.id, "", this.fuel, this.transmission, this.availability);
	}
	@Test(expected = TestDriveException.class)
	public void emptyFuel() {
		new Vehicle(this.id, this.model, "", this.transmission, this.availability);
	}
	@Test(expected = TestDriveException.class)
	public void emptyTransmission() {
		new Vehicle(this.id, this.model, this.fuel, "", this.availability);
	}

}
