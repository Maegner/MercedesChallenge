package pt.sinfo.testDrive.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.sinfo.testDrive.exception.TestDriveException;
import pt.sinfo.testDrive.exception.VehicleNotFoundException;
import pt.sinfo.testDrive.exception.VehicleUnavailableException;


public class RootMethodsTest {
	
	private Root root;
	private ArrayList<Vehicle> dealervehicles1;
	private ArrayList<Vehicle> dealervehicles2;
	
	public Vehicle vehicleSetUp(String vId) {
		String id = vId;
		String model = "AMG";
		String fuel = "ELECTRIC";
		String transmission = "AUTO";
		HashMap<String,ArrayList<Integer>> availability = new HashMap<String,ArrayList<Integer>>();
		ArrayList<Integer> dayOne = new ArrayList<Integer>();
		ArrayList<Integer> dayTwo = new ArrayList<Integer>();
		dayOne.add(1000);dayOne.add(1030);
		dayTwo.add(1000);dayTwo.add(1030);
		availability.put("thursday",dayOne);
		availability.put("monday", dayTwo);
		return new Vehicle(id,model,fuel,transmission,availability);
	}
	public Dealer dealerSetUp(ArrayList<Vehicle>dealerVehicles,String dId) {
		String id = dId;
		String name = "name";
		Integer latitude = 0;
		Integer longitude = 0;
		HashSet<String> closed = new HashSet<String>();
		return new Dealer(id, name, latitude, longitude, dealerVehicles, closed);
	}
	public Booking bookingSetUp(String vId,String bId) {
		String id = bId;
		String vehicleId = vId;
		String firstName = "Joanna";
		String lastName = "Randolph";
		DateTime pickupDateTime = new DateTime(2018,3,5,10,30);
		DateTime createdAt = new DateTime();
		DateTime cancelledAt = null;
		String cancelledReason = null;
		return new Booking(id, vehicleId,firstName,
				lastName,pickupDateTime,createdAt,cancelledAt,cancelledReason);
	}
	
	@Before
	public void setUp() {
		
		String vehicleA = "A";
		String vehicleB = "B";
		String vehicleC = "C";
		String vehicleD = "D";
		
		dealervehicles1 = new ArrayList<Vehicle>();
		dealervehicles1.add(vehicleSetUp(vehicleA));
		dealervehicles1.add(vehicleSetUp(vehicleB));
		
		dealervehicles2 = new ArrayList<Vehicle>();
		dealervehicles2.add(vehicleSetUp(vehicleC));
		dealervehicles2.add(vehicleSetUp(vehicleD));
		
		String dealerID1 = "1";
		String dealerID2 = "2";
		
		Dealer dealer1 = dealerSetUp(dealervehicles1, dealerID1);
		Dealer dealer2 = dealerSetUp(dealervehicles2, dealerID2);
		
		String bookingIdX = "X";
		String bookingIdZ = "Z";
		
		Booking booking1 = bookingSetUp(vehicleA, bookingIdX);
		Booking booking2 = bookingSetUp(vehicleB, bookingIdZ);
		
		HashMap<DateTime,Booking> vehicleABookings = new HashMap<DateTime,Booking>();
		vehicleABookings.put(booking1.getPickupDate(),booking1);
		
		HashMap<DateTime,Booking> vehicleBBookings = new HashMap<DateTime,Booking>();
		vehicleBBookings.put(booking2.getPickupDate(),booking2);
		
		HashMap<String,HashMap<DateTime,Booking>> bookings = new HashMap<String,HashMap<DateTime,Booking>>();
		bookings.put(vehicleA,vehicleABookings);
		bookings.put(vehicleB,vehicleBBookings);
		
		HashMap<String,Dealer> dealers = new HashMap<String,Dealer>();
		dealers.put(dealerID1, dealer1);
		dealers.put(dealerID2,dealer2);
		
		this.root = new Root(dealers, bookings);
		
	}
	
	//SEARCH BY DEALER
	@Test
	public void vehicleByExistingDealer() {
		List<Vehicle> dealersVeihicles = root.vehicleByDealer("1");
		Assert.assertEquals(this.dealervehicles1,dealersVeihicles);
	}
	@Test
	public void vehicleByNonExistingDealer() {
		List<Vehicle> dealersVeihicles = root.vehicleByDealer("45");
		Assert.assertEquals(new ArrayList<Dealer>(),dealersVeihicles);
	}
	@Test (expected = TestDriveException.class)
	public void vehicleByNullDealer() {
		root.vehicleByDealer(null);
	}
	@Test (expected = TestDriveException.class)
	public void vehicleByEmptyDealer() {
		root.vehicleByDealer("");
	}
	
	//SEARCH BY MODEL
	@Test
	public void vehicleByExistingModel() {
		List<Vehicle> searchResult = root.vehicleByModel("AMG");
		List<Vehicle> expected = new ArrayList<Vehicle>();
		expected.addAll(dealervehicles1);
		expected.addAll(dealervehicles2);
		Assert.assertEquals(expected,searchResult);
	}
	@Test
	public void vehicleByNonExistingModel() {
		List<Vehicle> searchResult = root.vehicleByModel("ZZZ");
		List<Vehicle> expected = new ArrayList<Vehicle>();
		Assert.assertEquals(expected,searchResult);
	}
	@Test (expected = TestDriveException.class)
	public void vehicleByNullModel() {
		root.vehicleByModel(null);
	}
	@Test (expected = TestDriveException.class)
	public void vehicleByEmptyModel() {
		root.vehicleByModel("");
	}
	
	//SEARCH BY TRANSMISSION
	@Test
	public void vehicleByExistingTransmission() {
		List<Vehicle> searchResult = root.vehicleByTransmission("AUTO");
		List<Vehicle> expected = new ArrayList<Vehicle>();
		expected.addAll(dealervehicles1);
		expected.addAll(dealervehicles2);
		Assert.assertEquals(expected,searchResult);
	}
	@Test
	public void vehicleByNonExistingTransmission() {
		List<Vehicle> searchResult = root.vehicleByTransmission("AAA");
		List<Vehicle> expected = new ArrayList<Vehicle>();
		Assert.assertEquals(expected,searchResult);
	}
	@Test (expected = TestDriveException.class)
	public void vehicleByNullTransmission() {
		root.vehicleByTransmission(null);
	}
	@Test (expected = TestDriveException.class)
	public void vehicleByEmptyTransmission() {
		root.vehicleByTransmission("");
	}
	//SEARCH BY FUEL
	@Test
	public void vehicleByExistingFuel() {
		List<Vehicle> searchResult = root.vehicleByFuel("ELECTRIC");
		List<Vehicle> expected = new ArrayList<Vehicle>();
		expected.addAll(dealervehicles1);
		expected.addAll(dealervehicles2);
		Assert.assertEquals(expected,searchResult);
	}
	@Test
	public void vehicleByNonExistingFuel() {
		List<Vehicle> searchResult = root.vehicleByFuel("KKK");
		List<Vehicle> expected = new ArrayList<Vehicle>();
		Assert.assertEquals(expected,searchResult);
	}
	@Test (expected = TestDriveException.class)
	public void vehicleByNullFuel() {
		root.vehicleByFuel(null);
	}
	@Test (expected = TestDriveException.class)
	public void vehicleByEmptyFuel() {
		root.vehicleByFuel("  ");
	}
	//SEARCH BY ID
	@Test (expected = TestDriveException.class)
	public void vehicleByNullId() {
		root.vehicleById(null);
	}
	
	//TESTING AVAILABILITY
	@Test
	public void success() {
		Vehicle v = this.dealervehicles1.get(0);
		DateTime date = (DateTime) this.root.bookings.get(v.getId()).keySet().toArray()[0];
		Assert.assertEquals(false, this.root.isAvailable(v.getId(), date));
		date = new DateTime(2090,12,10,10,50);
		Assert.assertEquals(true, this.root.isAvailable(v.getId(), date));
		
	}
	@Test(expected = VehicleNotFoundException.class)
	public void availabilityOfNonExistingVehicle() {
		this.root.isAvailable("AAA", new DateTime());
	}
	@Test(expected = TestDriveException.class)
	public void availabilityOfNullVehicle() {
		this.root.isAvailable(null, new DateTime());
	}
	@Test(expected = TestDriveException.class)
	public void availabilityOfEmptyVehicle() {
		this.root.isAvailable("", new DateTime());
	}
	@Test(expected = TestDriveException.class)
	public void availabilityOfNullDate(){
		this.root.isAvailable("AAA", null);
	}
	@Test(expected = TestDriveException.class)
	public void availabilityOfPastDate(){
		DateTime date = new DateTime(1090,12,10,10,50);
		this.root.isAvailable("AAA", date);
	}
	
	//Testing Booking a TestDrive
	@Test
	public void successfullBooking() {
		Booking book = this.bookingSetUp("C", "5");
		this.root.bookVehicle("2",book);
		boolean actual = this.root.isAvailable("C", book.getPickupDate());
		Assert.assertEquals(false, actual);
		
		//Testing 2 Bookings on the same car at different times
		DateTime date = new DateTime(2018,3,5,10,00);
		book.setPickupDate(date);
		this.root.bookVehicle("2",book);
		actual = this.root.isAvailable("C", book.getPickupDate());
		Assert.assertEquals(false, actual);
	}
	@Test(expected = VehicleUnavailableException.class)
	public void ocupiedBooking() {
		Booking book = this.bookingSetUp("A", "5");
		this.root.bookVehicle("1",book);
		this.root.isAvailable("A", book.getPickupDate());
	}
	@Test(expected = VehicleUnavailableException.class)
	public void unauthorizedDayBooking() {
		Booking book = this.bookingSetUp("A", "5");
		DateTime date = new DateTime(2018,3,7,10,30);
		book.setPickupDate(date);
		this.root.bookVehicle("1",book);
	}
	@Test(expected = VehicleUnavailableException.class)
	public void unauthorizedHourBooking() {
		Booking book = this.bookingSetUp("A", "5");
		DateTime date = new DateTime(2018,3,5,11,30);
		book.setPickupDate(date);
		this.root.bookVehicle("1",book);
	}
	@Test(expected = VehicleUnavailableException.class)
	public void unauthorizedMinuteBooking() {
		Booking book = this.bookingSetUp("A", "5");
		DateTime date = new DateTime(2018,3,5,10,50);
		book.setPickupDate(date);
		this.root.bookVehicle("1",book);
	}
	@Test(expected = TestDriveException.class)
	public void wrongDealer() {
		Booking book = this.bookingSetUp("A", "5");
		DateTime date = new DateTime(2018,3,8,10,00);
		book.setPickupDate(date);
		this.root.bookVehicle("2",book);
	}
	@Test(expected = TestDriveException.class)
	public void unexistantDealer() {
		Booking book = this.bookingSetUp("A", "5");
		DateTime date = new DateTime(2018,3,8,10,00);
		book.setPickupDate(date);
		this.root.bookVehicle("90",book);
	}
	@Test(expected = TestDriveException.class)
	public void nullDealer() {
		Booking book = this.bookingSetUp("A", "5");
		DateTime date = new DateTime(2018,3,8,10,00);
		book.setPickupDate(date);
		this.root.bookVehicle(null,book);
	}
	@Test(expected = TestDriveException.class)
	public void nullBooking() {
		Booking book = null;
		this.root.bookVehicle("2",book);
	}
	
	
}
