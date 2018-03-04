package pt.sinfo.testDrive.domain;

public class Coordinate {
	private int latitude;
	private int longitude;
	
	public Coordinate(int longitude, int latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public Integer getLatitude() {
		return this.latitude;
	}
	public Integer getLongitude() {
		return this.longitude;
	}
	public void setLongitude(int longi) {
		this.longitude = longi;
	}
	public void setLatitude(int lati) {
		this.latitude = lati;
	}

}
