package pt.sinfo.testDrive.domain;


import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.sinfo.testDrive.exception.TestDriveException;

public class BookingConstructorMethodTest {
	
	private String id;
	private String vehicleId;
	private String firstName;
	private String lastName;
	private DateTime pickupDateTime;
	private DateTime createdAt;
	private DateTime cancelledAt;
	private String cancelledReason;
	
	@Before
	public void setUp() {
		this.id = "1c6bd910-12b1-45d6-b4d8-cdff2f37db90";
		this.vehicleId = "3928f780-295b-4dd0-8cc9-28c0738398d9";
		this.firstName = "Joanna";
		this.lastName = "Randolph";
		this.pickupDateTime = new DateTime();
		this.cancelledAt = null;
		this.cancelledReason = null;
	}
	
	@Test
	public void successConstructor1() {
		Booking testSubject = new Booking(this.id, this.vehicleId, this.firstName,
				this.lastName, this.pickupDateTime);
		Assert.assertEquals(this.id, testSubject.getId());
		Assert.assertEquals(this.vehicleId,testSubject.getVehicleId());
		Assert.assertEquals(this.firstName, testSubject.getFirstName());
		Assert.assertEquals(this.lastName,testSubject.getLastName());
		Assert.assertEquals(this.pickupDateTime, testSubject.getPickupDate());
	}
	@Test(expected = TestDriveException.class)
	public void nullIdconstructor1() {
		new Booking(null, vehicleId, firstName, lastName, pickupDateTime);
	}
	@Test(expected = TestDriveException.class)
	public void nullVehicleIdconstructor1() {
		new Booking(id, null, firstName, lastName, pickupDateTime);
	}
	@Test(expected = TestDriveException.class)
	public void nullFirstNameconstructor1() {
		new Booking(id, vehicleId, null, lastName, pickupDateTime);
	}
	@Test(expected = TestDriveException.class)
	public void emptyLastNameconstructor1() {
		new Booking(id, vehicleId, firstName, "", pickupDateTime);
	}
	@Test(expected = TestDriveException.class)
	public void nullPickupTimeconstructor1() {
		new Booking(id, vehicleId, firstName, lastName, null);
	}
	@Test
	public void successConstructor2() {
		Booking testSubject = new Booking(vehicleId, firstName, lastName, pickupDateTime);
		Assert.assertEquals(this.vehicleId,testSubject.getVehicleId());
		Assert.assertEquals(this.firstName, testSubject.getFirstName());
		Assert.assertEquals(this.lastName,testSubject.getLastName());
		Assert.assertEquals(this.pickupDateTime, testSubject.getPickupDate());
		DateTime canceldate = new DateTime();
		testSubject.cancel("reason", canceldate);
		Assert.assertEquals("reason", testSubject.getCancelledReason());
		Assert.assertEquals(canceldate,testSubject.getCancelledAt());
	}
}
