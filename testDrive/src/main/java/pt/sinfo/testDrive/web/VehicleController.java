package pt.sinfo.testDrive.web;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.sinfo.testDrive.domain.Root;
import pt.sinfo.testDrive.domain.Vehicle;

@RestController
@EnableAutoConfiguration
public class VehicleController {
	
	@RequestMapping(value="/vehicles" ,method = RequestMethod.POST)
	public List<Vehicle>search(@RequestBody(required=true)Map<String,String> querie){
		
		String model = querie.get("model");
		if(model==null) {
			model = "";
		}
		String fuel = querie.get("fuel");
		if(fuel==null) {
			fuel = "";
		}
		String transmission = querie.get("transmission");
		if(transmission==null) {
			transmission = "";
		}
		String dealerId = querie.get("dealerId");
		if(dealerId==null) {
			dealerId = "";
		}
		
		Root root = Root.getReference();
		return root.searchVehicle(model, fuel, transmission, dealerId);
	}
}
