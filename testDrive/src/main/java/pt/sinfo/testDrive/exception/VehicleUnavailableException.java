package pt.sinfo.testDrive.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.LOCKED)
public class VehicleUnavailableException extends TestDriveException {
	public VehicleUnavailableException() {
		super();
	}

}
