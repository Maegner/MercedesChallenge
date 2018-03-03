package pt.sinfo.testDrive.domain;

import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import pt.sinfo.testDrive.exception.TestDriveException;

public class Vehicle {
	private String id;
	private String model;
	private String fuel;
	private String transmission;
	private HashMap<String,ArrayList<Integer>> availability;
	
	public Vehicle(String id, String model,String fuel,String transmission,HashMap<String,ArrayList<Integer>> availability) {
		
		if(id == null || id.trim().equals("") 
		|| model == null || model.trim().equals("")
		|| fuel == null || fuel.trim().equals("")
		|| transmission == null || transmission.trim().equals("") || availability == null) {
			throw new TestDriveException();
		}
		
		this.id = id;
		this.model = model;
		this.fuel = fuel;
		this.transmission = transmission;
		this.availability = availability;
	}
	

	public String getId() {
		return id;
	}
	public String getModel() {
		return model;
	}
	public String getFuel() {
		return fuel;
	}
	public String getTransmission() {
		return transmission;
	}
	public HashMap<String,ArrayList<Integer>> getAvailability() {
		return availability;
	}
	public boolean checkAvailability(DateTime date) {
		boolean found = false;
		for(String day : availability.keySet()) {
			if (day.equals("monday") && date.dayOfWeek().get()==DateTimeConstants.MONDAY) {
				for(int time: availability.get(day)) {
					if(time == date.getHourOfDay()*100 + date.getMinuteOfHour()) {
						found = true;
					}
				}
				if(found) {break;}
			}
			else if (day.equals("tuesday") && date.dayOfWeek().get()==DateTimeConstants.TUESDAY) {
				for(int time: availability.get(day)) {
					if(time/100 == date.getHourOfDay() && time%100 == date.getMinuteOfHour()) {
						found = true;
					}
				}
				if(found) {break;}
			}
			else if (day.equals("wednesday") && date.dayOfWeek().get()==DateTimeConstants.WEDNESDAY) {
				for(int time: availability.get(day)) {
					if(time/100 == date.getHourOfDay() && time%100 == date.getMinuteOfHour()) {
						found = true;
					}
				}
				if(found) {break;}
			}
			else if (day.equals("thursday") && date.dayOfWeek().get()==DateTimeConstants.THURSDAY) {
				for(int time: availability.get(day)) {
					if(time/100 == date.getHourOfDay() && time%100 == date.getMinuteOfHour()) {
						found = true;
					}
				}
				if(found) {break;}
			}
			else if (day.equals("friday") && date.dayOfWeek().get()==DateTimeConstants.FRIDAY) {
				for(int time: availability.get(day)) {
					if(time/100 == date.getHourOfDay() && time%100 == date.getMinuteOfHour()) {
						found = true;
					}
				}
				if(found) {break;}
			}
			else if (day.equals("saturday") && date.dayOfWeek().get()==DateTimeConstants.SATURDAY) {
				for(int time: availability.get(day)) {
					if(time/100 == date.getHourOfDay() && time%100 == date.getMinuteOfHour()) {
						found = true;
					}
				}
				if(found) {break;}
			}
			else if (day.equals("sunday") && date.dayOfWeek().get()==DateTimeConstants.SUNDAY) {
				for(int time: availability.get(day)) {
					if(time/100 == date.getHourOfDay() && time%100 == date.getMinuteOfHour()) {
						found = true;
					}
				}
				if(found) {break;}
			}
		}
		return found;
	}
	
}
