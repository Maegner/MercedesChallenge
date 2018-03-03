package pt.sinfo.testDrive.domain;

import java.util.Date;

import pt.sinfo.testDrive.exception.TestDriveException;

public class Booking {
	private String id;
	private String vehicleId;
	private String firstName;
	private String lastName;
	private Date pickupDate;
	private Date createdAt;
	private Date cancelledAt;
	private String cancelledReason;
	
	public boolean verify(String id,String vehicleId,String firstName,String lastName
			,Date pickupDate,Date createdAt,Date cancelledAt,String cancelledReason) {
		return id==null||id.trim().equals("")||vehicleId==null||vehicleId.trim().equals("")
				|| firstName==null||firstName.trim().equals("") || lastName==null||lastName.trim().equals("")
				|| pickupDate==null || createdAt==null
				|| (cancelledAt != null && (cancelledReason==null|| cancelledReason.trim().equals("")));
	}
	
	public Booking(String id,String vehicleId,String firstName,String lastName
			,Date pickupDate,Date createdAt,Date cancelledAt,String cancelledReason){
		
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
	public Date getCreatedAt() {
		return createdAt;
	}
	public String getFirstName() {
		return firstName;
	}
	public Date getPickupDate() {
		return pickupDate;
	}
	public Date getCancelledAt() {
		return cancelledAt;
	}
	public String getCancelledReason() {
		return cancelledReason;
	}
	
	
}
