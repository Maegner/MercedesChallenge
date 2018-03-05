package pt.sinfo.testDrive.domain;

public class Coordinate {
	private float latitude;
	private float longitude;
	
	public Coordinate() {
		
	}
	
	public Coordinate(float longitude, float latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public float getLatitude() {
		return this.latitude;
	}
	public float getLongitude() {
		return this.longitude;
	}
	public void setLongitude(int longi) {
		this.longitude = longi;
	}
	public void setLatitude(int lati) {
		this.latitude = lati;
	}

}
