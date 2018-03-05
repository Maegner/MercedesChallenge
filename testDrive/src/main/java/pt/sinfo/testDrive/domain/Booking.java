package pt.sinfo.testDrive.domain;



import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.sinfo.testDrive.exception.TestDriveException;

@JsonIgnoreProperties({ "createdAt","cancelledAt","cancelledReason","pickupDate" })
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
			,DateTime pickupDate2) {
		return verifyString(id)||verifyString(vehicleId)
				|| verifyString(firstName)|| verifyString(lastName)
				|| pickupDate2==null;
	}
	
	public Booking(String id,String vehicleId,String firstName,String lastName
			,DateTime pickupDate){
		
		if(verify(id,vehicleId,firstName,lastName,pickupDate)){
			throw new TestDriveException();
		}
		this.id = id;
		this.vehicleId = vehicleId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pickupDate = pickupDate;
		this.createdAt = new DateTime();
		this.cancelledAt = null;
		this.cancelledReason = null;
	}
	public Booking(String id,String vehicleId,String firstName,String lastName
			,DateTime pickupDate,DateTime createdAt){
		
		if(verify(id,vehicleId,firstName,lastName,pickupDate)){
			throw new TestDriveException();
		}
		this.id = id;
		this.vehicleId = vehicleId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pickupDate = pickupDate;
		this.createdAt = createdAt;
		this.cancelledAt = null;
		this.cancelledReason = null;
	}
	public Booking(String vehicleId,String firstName,String lastName
			,DateTime pickupDate){
		
		if(verify("id",vehicleId,firstName,lastName,pickupDate)){
			throw new TestDriveException();
		}
		this.id = ""+this.hashCode();
		this.vehicleId = vehicleId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pickupDate = pickupDate;
		this.createdAt = new DateTime();
		this.cancelledAt = null;
		this.cancelledReason = null;
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
	public String getFirstName() {
		return firstName;
	}
	public DateTime getPickupDate() {
		return pickupDate;
	}
	public void setPickupDate(DateTime date) {
		this.pickupDate = date;
	}
	public DateTime getCancelledAt() {
		return cancelledAt;
	}
	public String getCancelledReason() {
		return cancelledReason;
	}
	public void cancel(String reason) {
		this.cancelledAt = new DateTime();
		this.cancelledReason = reason;
	}
	public void cancel(String reason,DateTime cancelledAt) {
		this.cancelledAt = cancelledAt;
		this.cancelledReason = reason;
	}
	
	
}
