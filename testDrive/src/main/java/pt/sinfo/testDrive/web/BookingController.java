package pt.sinfo.testDrive.web;

import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import pt.sinfo.testDrive.domain.Booking;
import pt.sinfo.testDrive.domain.Root;
import pt.sinfo.testDrive.exception.TestDriveException;

@RestController
@EnableAutoConfiguration
public class BookingController {
	
	@RequestMapping(value="bookings/new" ,method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void bookTestDrive(@RequestParam(required=true) String dealerId,
			@RequestBody(required=true) Map<String,String> bookingInfo) {
		
		String vehicleId = bookingInfo.get("vehicleId");
		String firstName = bookingInfo.get("firstName");
		String lastName = bookingInfo.get("lastName");
		String date = bookingInfo.get("pickupDate");
		DateTime pickupDate = null;
		
		try {
			String pattern = "yyyy-mm-ddThh:mm:ss.SSS";
			pickupDate = DateTime.parse(date,DateTimeFormat.forPattern(pattern));
		}catch(Exception e) {
			throw new TestDriveException();
		}
		
		Booking booking = new Booking(vehicleId, firstName, lastName, pickupDate);
		Root.getReference().bookVehicle(dealerId, booking);
	}
	@RequestMapping(value="bookings/cancel" ,method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void cancelBooking(@RequestParam(required=true) String bookingId,
			@RequestBody(required=true) String reason) {
		Root.getReference().cancelBooking(bookingId, reason);
	}
}
