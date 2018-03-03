package pt.sinfo.testDrive.domain;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BookingConstructorMethodTest {
	
	private String id;
	private String vehicleId;
	private String firstName;
	private String lastName;
	private Date pickupDate;
	private Date createdAt;
	private Date cancelledAt;
	private String cancelledReason;
	
	@Before
	public void setUp() {
		this.id = "1c6bd910-12b1-45d6-b4d8-cdff2f37db90";
		this.vehicleId = "3928f780-295b-4dd0-8cc9-28c0738398d9";
		this.firstName = "Joanna";
		this.lastName = "Randolph";
		this.pickupDate = new Date();
		this.createdAt = new Date();
		this.cancelledAt = null;
		this.cancelledReason = null;
	}
	
	@Test
	public void success() {
		Booking testSubject = new Booking(this.id, this.vehicleId, this.firstName,
				this.lastName, this.pickupDate, this.createdAt, this.cancelledAt, this.cancelledReason);
		Assert.assertEquals(this.id, testSubject.getId());
		Assert.assertEquals(this.vehicleId,testSubject.getVehicleId());
		Assert.assertEquals(this.firstName, testSubject.getFirstName());
		Assert.assertEquals(this.lastName,testSubject.getLastName());
		Assert.assertEquals(this.pickupDate, testSubject.getPickupDate());
		Assert.assertEquals(this.createdAt, testSubject.getCreatedAt());
		Assert.assertEquals(this.cancelledAt,testSubject.getCancelledAt());
		Assert.assertEquals(this.cancelledReason, testSubject.getCancelledReason());
	}
}
