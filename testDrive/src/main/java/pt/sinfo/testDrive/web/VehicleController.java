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
	
	@RequestMapping(value="/vehicles/" ,method = RequestMethod.GET)
	public List<Vehicle>search(@RequestParam(defaultValue="") String fuel,
			@RequestParam(defaultValue="") String model,
			@RequestParam(defaultValue="") String transmission,
			@RequestParam(defaultValue="")String dealerId){
		Root root = Root.getReference();
		return root.searchVehicle(model, fuel, transmission, dealerId);
	}
}
