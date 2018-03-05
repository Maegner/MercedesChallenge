package pt.sinfo.testDrive.domain;

import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VehicleCheckAvailabilityMethodTest {
	
	private Vehicle monday;
	private Vehicle tuesday;
	private Vehicle wednesday;
	private Vehicle thursday;
	private Vehicle friday;
	private Vehicle saturday;
	private Vehicle sunday;
	private ArrayList<DateTime> dates;
		
	public Vehicle vehicleSetUp(String day) {
		String id = "nono";
		String model = "AMG";
		String fuel = "ELECTRIC";
		String transmission = "AUTO";
		HashMap<String,ArrayList<Integer>> availability = new HashMap<String,ArrayList<Integer>>();
		ArrayList<Integer> dayOne = new ArrayList<Integer>();
		dayOne.add(1000);dayOne.add(1030);
		availability.put(day,dayOne);
		return new Vehicle(id,model,fuel,transmission,availability);
	}
	
	@Before
	public void setUp() {
		monday = this.vehicleSetUp("monday");
		tuesday = this.vehicleSetUp("tuesday");
		wednesday = this.vehicleSetUp("wednesday");
		thursday = this.vehicleSetUp("thursday");
		friday = this.vehicleSetUp("friday");
		saturday = this.vehicleSetUp("saturday");
		sunday = this.vehicleSetUp("sunday");
		
		dates = new ArrayList<DateTime>();
		
		DateTime date = new DateTime(2018,3,12,11,30);
		dates.add(date);
		date = new DateTime(2018,3,13,11,30);
		dates.add(date);
		date = new DateTime(2018,3,14,11,30);
		dates.add(date);
		date = new DateTime(2018,3,15,11,30);
		dates.add(date);
		date = new DateTime(2018,3,16,11,30);
		dates.add(date);
		date = new DateTime(2018,3,17,11,30);
		dates.add(date);
		date = new DateTime(2018,3,18,11,30);
		dates.add(date);
	}
	
	@Test
	public void notAvailable() {
		for(DateTime d: dates) {
			Assert.assertTrue(!monday.checkAvailability(d));
			Assert.assertTrue(!tuesday.checkAvailability(d));
			Assert.assertTrue(!wednesday.checkAvailability(d));
			Assert.assertTrue(!thursday.checkAvailability(d));
			Assert.assertTrue(!friday.checkAvailability(d));
			Assert.assertTrue(!saturday.checkAvailability(d));
			Assert.assertTrue(!sunday.checkAvailability(d));
		}
	}
	@Test
	public void availableMonday() {
		boolean condition = monday.checkAvailability(new DateTime(2018,3,12,10,30));
		Assert.assertTrue(condition);
	}
	@Test
	public void availableTuesday() {
		boolean condition = tuesday.checkAvailability(new DateTime(2018,3,13,10,30));
		Assert.assertTrue(condition);
	}
	@Test
	public void availableWednesday() {
		boolean condition = wednesday.checkAvailability(new DateTime(2018,3,14,10,30));
		Assert.assertTrue(condition);
	}
	@Test
	public void availableThursday() {
		boolean condition = thursday.checkAvailability(new DateTime(2018,3,15,10,30));
		Assert.assertTrue(condition);
	}
	@Test
	public void availableFriday() {
		boolean condition = friday.checkAvailability(new DateTime(2018,3,16,10,30));
		Assert.assertTrue(condition);
	}
	@Test
	public void availableSaturday() {
		boolean condition = saturday.checkAvailability(new DateTime(2018,3,17,10,30));
		Assert.assertTrue(condition);
	}
	@Test
	public void availableSunday() {
		boolean condition = sunday.checkAvailability(new DateTime(2018,3,18,10,30));
		Assert.assertTrue(condition);
	}
	
}
