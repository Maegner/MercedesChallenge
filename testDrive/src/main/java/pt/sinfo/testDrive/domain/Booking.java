package pt.sinfo.testDrive.domain;

import java.util.Date;

import org.joda.time.DateTime;

import pt.sinfo.testDrive.exception.TestDriveException;

public class Booking {
	private String id;
	private String vehicleId;
	private String firstName;
	private String lastName;
	private DateTime pickupDate;
	private DateTime createdAt;
	private DateTime cancelledAt;
	private String cancelledReason;
	
	public boolean verifyString(String s) {
		return s == null || s.trim().equals("");
	}
	
	public boolean verify(String id,String vehicleId,String firstName,String lastName
			,DateTime pickupDate2,DateTime createdAt2,DateTime cancelledAt2,String cancelledReason) {
		return verifyString(id)||verifyString(vehicleId)
				|| verifyString(firstName)|| verifyString(lastName)
				|| pickupDate2==null || createdAt2==null
				|| (cancelledAt2 != null && verifyString(cancelledReason));
	}
	
	public Booking(String id,String vehicleId,String firstName,String lastName
			,DateTime pickupDate,DateTime createdAt,DateTime cancelledAt,String cancelledReason){
		
		if(verify(id,vehicleId,firstName,lastName,pickupDate,createdAt,cancelledAt,cancelledReason)){
			throw new TestDriveException();
		}
		this.id = id;
		this.vehicleId = vehicleId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pickupDate = pickupDate;
		this.createdAt = createdAt;
		this.cancelledAt = cancelledAt;
		this.cancelledReason = cancelledReason;
	}
	
	
	public String getVehicleId() {
		return vehicleId;
	}
	public String getId() {
		return id;
	}
	public String getLastName() {
		return lastName;
	}
	public DateTime getCreatedAt() {
		return createdAt;
	}
	public String getFirstName() {
		return firstName;
	}
	public DateTime getPickupDate() {
		return pickupDate;
	}
	public DateTime getCancelledAt() {
		return cancelledAt;
	}
	public String getCancelledReason() {
		return cancelledReason;
	}
	
	
}
