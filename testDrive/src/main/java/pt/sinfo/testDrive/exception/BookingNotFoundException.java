package pt.sinfo.testDrive.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookingNotFoundException extends TestDriveException {
	public BookingNotFoundException() {
		super();
	}

}
