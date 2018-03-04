package pt.sinfo.testDrive.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TestDriveException extends RuntimeException {
	
	public TestDriveException() {
		super();
	}

}
