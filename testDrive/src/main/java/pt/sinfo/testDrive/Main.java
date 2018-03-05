package pt.sinfo.testDrive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import pt.sinfo.testDrive.utils.DataParser;

@SpringBootApplication
public class Main {
	public static void main(String[]args) {
		DataParser.populate();
		SpringApplication.run(Main.class, args);
		
	}
}
