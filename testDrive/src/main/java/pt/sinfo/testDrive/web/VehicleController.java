package pt.sinfo.testDrive.web;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.sinfo.testDrive.domain.Root;
import pt.sinfo.testDrive.domain.Vehicle;

@RestController
@EnableAutoConfiguration
public class VehicleController {
	
	@RequestMapping(value="vehicles/" ,method = RequestMethod.GET)
	public List<Vehicle> vehiclesByModel(@RequestParam(required=true) String model){
		Root root = Root.getReference();
		return root.vehicleByModel(model);
	}
	@RequestMapping(value="vehicles/" ,method = RequestMethod.GET)
	public List<Vehicle> vehiclesByFuel(@RequestParam(required=true) String fuel){
		Root root = Root.getReference();
		return root.vehicleByFuel(fuel);
	}
	@RequestMapping(value="vehicles/" ,method = RequestMethod.GET)
	public List<Vehicle> vehiclesByTransmission(@RequestParam(required=true) String transmission){
		Root root = Root.getReference();
		return root.vehicleByTransmission(transmission);
	}
	@RequestMapping(value="vehicles/" ,method = RequestMethod.GET)
	public List<Vehicle> vehiclesByDealer(@RequestParam(required=true) String dealer){
		Root root = Root.getReference();
		return root.vehicleByDealer(dealer);
	}
	
}
