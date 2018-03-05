package pt.sinfo.testDrive.web;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.sinfo.testDrive.domain.Coordinate;
import pt.sinfo.testDrive.domain.Dealer;
import pt.sinfo.testDrive.domain.Root;
import pt.sinfo.testDrive.domain.Vehicle;
import pt.sinfo.testDrive.exception.TestDriveException;

@RestController
@EnableAutoConfiguration
public class DealerController {
	
	@RequestMapping(value="/dealers/get-closest" ,method = RequestMethod.POST)
	public Dealer closestDealerByDescription(@RequestParam(defaultValue="") String fuel,
			@RequestParam(defaultValue="") String model,@RequestParam(defaultValue="") String transmission,
			@RequestBody(required=true) Coordinate userLocation){
		Root root = Root.getReference();
		return root.closestDealer(model, fuel, transmission, userLocation);
	}
	
	@RequestMapping(value="/dealers/search" ,method = RequestMethod.POST)
	public List<Dealer> sortedDealersByDescription(@RequestParam(defaultValue="") String fuel,
			@RequestParam(defaultValue="") String model,@RequestParam(defaultValue="") String transmission,
			@RequestBody(required=true) Coordinate userLocation){
		Root root = Root.getReference();
		return root.dealersWithSpecdVehicles(model, fuel, transmission, userLocation);
	}
	
	@RequestMapping(value="/dealers/search-in-poligon" ,method = RequestMethod.POST)
	public List<Dealer> dealersInPoligonByDescription(@RequestParam(defaultValue="") String fuel,
			@RequestParam(defaultValue="") String model,@RequestParam(defaultValue="") String transmission,
			@RequestBody(required=true) Map<String,Coordinate> boundaries){
		
		Root root = Root.getReference();
		Coordinate leftBoundary = boundaries.get("bottomLeft");
		Coordinate rightBoundary = boundaries.get("topRight");
		
		if(leftBoundary==null||rightBoundary==null) {throw new TestDriveException();}
		else if(leftBoundary.getLatitude()>= rightBoundary.getLatitude()
				|| leftBoundary.getLongitude()>=rightBoundary.getLongitude()) {
			throw new TestDriveException();
		}
		return root.dealersInPoligon(model, fuel, transmission, rightBoundary, leftBoundary);
		
	}
	
}
