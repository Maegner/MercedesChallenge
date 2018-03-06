package pt.sinfo.testDrive.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.sinfo.testDrive.domain.Booking;
import pt.sinfo.testDrive.domain.Root;

public class ParserPopulateMethodTest {
	
	@Before
	public void setUp() {
		DataParser.populate();
	}
	
	@Test
	public void verifyBookingImport() {
		Root root = Root.getReference();
		Assert.assertTrue(root.getBookings().containsKey("44a36bfa-ec8f-4448-b4c2-809203bdcb9e"));
		ArrayList<Booking> b = root.getBookings().get("44a36bfa-ec8f-4448-b4c2-809203bdcb9e");
		boolean hasBooking = false;
		for(Booking book : b){
			if(book.getId().equals("1c6bd910-12b1-45d6-b4d8-cdff2f37db90")) {
				hasBooking = true;
				break;
			}
		}
		Assert.assertTrue(hasBooking);
	}
	@Test
	public void verifyDealerImport() {
		Root root = Root.getReference();
		Assert.assertTrue(root.getDealers().containsKey("846679bd-5831-4286-969b-056e9c89d74c"));
	}
	
	@Test
	public void verifyVehicles() {
		Root root = Root.getReference();
		Assert.assertTrue(root.searchVehicle("E", "", "", "").size()==4);
	}

}
