package pt.sinfo.testDrive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import pt.sinfo.testDrive.utils.DataParser;

@SpringBootApplication
public class Main {
	
	public static void main(String[]args) {
		DataParser.populate();
		SpringApplication.run(Main.class, args);		
	}
}
