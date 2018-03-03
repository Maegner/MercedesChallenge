package pt.sinfo.testDrive.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.sinfo.testDrive.exception.TestDriveException;

public class DealerConstructorMethodTest {
	
	private String id;
	private String name;
	private Integer latitude;
	private Integer longitude;
	private ArrayList<Vehicle> vehicles;
	private HashSet<String> closed;
	
	@Before
	public void setUp() {
		this.id = "ab49f56a-451d-4721-8319-efdca5e69680";
		this.name = "name";
		this.latitude = 0;
		this.longitude = 0;
		HashMap<String,ArrayList<Integer>> availability = new HashMap<String,ArrayList<Integer>>();
		ArrayList<Integer> dayOne = new ArrayList<Integer>();
		ArrayList<Integer> dayTwo = new ArrayList<Integer>();
		dayOne.add(1000);dayOne.add(1030);
		dayTwo.add(1000);dayTwo.add(1030);
		availability.put("thursday",dayOne);
		availability.put("Monday", dayTwo);
		Vehicle vehicle = new Vehicle("id", "model", "fuel", "transmission", availability);
		ArrayList<Vehicle> vehicleArray = new ArrayList<Vehicle>();
		vehicleArray.add(vehicle);
		this.vehicles = vehicleArray;
		this.closed = new HashSet<String>();
	}
	
	@Test
	public void success() {
		Dealer testSubject = new Dealer(id, name, latitude, longitude, vehicles, closed);
		Assert.assertEquals(this.id, testSubject.getId());
		Assert.assertEquals(this.name, testSubject.getName());
		Assert.assertEquals(this.latitude, testSubject.getLatitude());
		Assert.assertEquals(this.longitude, testSubject.getLongitude());
		Assert.assertEquals(this.vehicles, testSubject.getVehicles().collect(Collectors.toList()));
		Assert.assertEquals(this.closed, testSubject.getClosed());
	}
	
	//TESTING FOR NULL ARGUMENTS
	@Test(expected = TestDriveException.class)
	public void nullID() {
		new Dealer(null, name, latitude, longitude, vehicles, closed);
	}
	@Test(expected = TestDriveException.class)
	public void nullName() {
		new Dealer(id, null, latitude, longitude, vehicles, closed);
	}
	@Test(expected = TestDriveException.class)
	public void nullLat() {
		new Dealer(id, name, null, longitude, vehicles, closed);
	}
	@Test(expected = TestDriveException.class)
	public void nullLong() {
		new Dealer(id, name, latitude, null, vehicles, closed);
	}
	@Test(expected = TestDriveException.class)
	public void nullVehicles() {
		new Dealer(id, name, latitude, longitude, null, closed);
	}
	
	//TESTING FOR EMPTY ARGUMENTS
	@Test(expected = TestDriveException.class)
	public void emptyID() {
		new Dealer("", name, latitude, longitude, vehicles, closed);
	}
	@Test(expected = TestDriveException.class)
	public void emptyName() {
		new Dealer(id, "", latitude, longitude, vehicles, closed);
	}

}
