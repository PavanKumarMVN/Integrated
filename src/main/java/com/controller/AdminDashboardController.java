package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.AdminService;
import com.dao.FareService;
import com.dao.FleetService;
import com.dao.FlightService;
import com.dao.LocationService;
import com.model.*;

@RestController
public class AdminDashboardController 
{
	
	@Autowired
	FlightService flightService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	FleetService fleetService;
	
	@Autowired
	FareService fareService;
	
	@Autowired
	LocationService locationService;
	
	// admin flight dashboard changes
	
		@PostMapping("/admin/addflight")
		public ResponseEntity<?> addFlight(@RequestBody AdminDashboard adminDashboard) throws AddFlightException
		{
			boolean result= adminService.checkAdmin(adminDashboard.getAdmin().getUserName(), adminDashboard.getAdmin().getPassword());
			
			if(result) {
				if(adminDashboard.getFlight().getArrivalLocation()==null || adminDashboard.getFlight().getDepartureLocation()==null ||
						adminDashboard.getFlight().getFleet()==null || adminDashboard.getFlight().getArrivalTime()==null || adminDashboard.getFlight().getDepartureTime()==null)
				   {
					   throw new AddFlightException();
				   }
				flightService.add(adminDashboard.getFlight());
				return ResponseEntity.status(HttpStatus.OK).body("Flight details added by admin Succesfully");
			}
			else {
				return ResponseEntity.status(HttpStatus.OK).body("Invalid Admin details");
			}
		}
		@DeleteMapping("/admin/deleteflight")
		 public ResponseEntity<?> deleteFlight(@RequestBody AdminDashboard adminDashboard) throws DeleteFlightException 
		 {
			boolean result= adminService.checkAdmin(adminDashboard.getAdmin().getUserName(), adminDashboard.getAdmin().getPassword());
			if(result)
			{
				Flight flight= flightService.find(adminDashboard.getFlight().getId());
				if(flight==null)
				{
					throw new DeleteFlightException(flight.getId());
				}
				flightService.delete(flight.getId());
				return ResponseEntity.ok("Flight details deleted successfully");
			}
			else
			{
				return ResponseEntity.ok("Admin details not found");
			}
		 }
		
		@PatchMapping("/admin/updateflight")
		public ResponseEntity<?> updateFlight(@RequestBody AdminDashboard ad) throws UpdateFlightException
		{
			boolean result= adminService.checkAdmin(ad.getAdmin().getUserName(), ad.getAdmin().getPassword());
			if(result)
			{
				Flight flight = flightService.find(ad.getFlight().getId());
				if(flight==null)
				{
					throw new UpdateFlightException();
				}
				flightService.update(ad.getFlight());
				return ResponseEntity.ok("Flight details updated");
			}
			else
			{
				return ResponseEntity.ok("Admin details not found");
			}
		}
		
		
	 
	 // admin location dashboard changes
	@PostMapping("/admin/addlocation")
	public ResponseEntity<?> addLocation(@RequestBody AdminDashboard adminDashboard) throws AddLocationException
	{
		boolean result= adminService.checkAdmin(adminDashboard.getAdmin().getUserName(), adminDashboard.getAdmin().getPassword());
		
		if(result) {
			if(adminDashboard.getLocation().getAirportName()==null || adminDashboard.getLocation().getCode()==null ||
					adminDashboard.getLocation().getCountry()==null || adminDashboard.getLocation().getName()==null)
			   {
				   throw new AddLocationException();
			   }
			locationService.add(adminDashboard.getLocation());
			return ResponseEntity.status(HttpStatus.OK).body("Location details added by admin Succesfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body("Invalid Admin details");
		}
	}
	
	@DeleteMapping("/admin/deletelocation")
	 public ResponseEntity<?> deleteLocation(@RequestBody AdminDashboard adminDashboard) throws DeleteLocationException 
	 {
		boolean result= adminService.checkAdmin(adminDashboard.getAdmin().getUserName(), adminDashboard.getAdmin().getPassword());
		if(result)
		{
			Location location= locationService.find(adminDashboard.getLocation().getId());
			if(location==null)
			{
				throw new DeleteLocationException(location.getId());
			}
			locationService.delete(location.getId());
			return ResponseEntity.ok("Location details deleted successfully");
		}
		else
		{
			return ResponseEntity.ok("Admin details not found");
		}
	 }
	
	@PatchMapping("/admin/updatelocation")
	public ResponseEntity<?> updateLocation(@RequestBody AdminDashboard ad) throws UpdateFlightException
	{
		boolean result= adminService.checkAdmin(ad.getAdmin().getUserName(), ad.getAdmin().getPassword());
		if(result)
		{
			Location location = locationService.find(ad.getLocation().getId());
			if(location==null)
			{
				throw new UpdateLocationException();
			}
			locationService.update(ad.getLocation());
			return ResponseEntity.ok("Location details updated");
		}
		else
		{
			return ResponseEntity.ok("Admin details not found");
		}
	}
	 
	 
	 
	 // admin fleet dashboard changes
	 
	@DeleteMapping("/admin/deletefleet")
	 public ResponseEntity<?> deleteFleet(@RequestBody AdminDashboard adminDashboard) throws DeleteFleetException 
	 {
		boolean result= adminService.checkAdmin(adminDashboard.getAdmin().getUserName(), adminDashboard.getAdmin().getPassword());
		if(result)
		{
			Fleet fleet= fleetService.find(adminDashboard.getFleet().getId());
			if(fleet==null)
			{
				throw new DeleteFleetException(fleet.getId());
			}
			fleetService.delete(fleet.getId());
			return ResponseEntity.ok("Fleet details deleted successfully");
		}
		else
		{
			return ResponseEntity.ok("Admin details not found");
		}
	 }
	
	@PatchMapping("/admin/updatefleet")
	public ResponseEntity<?> updateFleet(@RequestBody AdminDashboard ad) throws UpdateFlightException
	{
		boolean result= adminService.checkAdmin(ad.getAdmin().getUserName(), ad.getAdmin().getPassword());
		if(result)
		{
			Fleet fleet= fleetService.find(ad.getFleet().getId());
			if(fleet==null)
			{
				throw new DeleteFleetException(fleet.getId());
			}
			fleetService.update(ad.getFleet());
			return ResponseEntity.ok("Fleet details updated");
		}
		else
		{
			return ResponseEntity.ok("Admin details not found");
		}
	}
	 
	 // admin fare dashboard changes
	 
	@DeleteMapping("/admin/deletefare")
	 public ResponseEntity<?> deleteFare(@RequestBody AdminDashboard adminDashboard) throws DeleteFareException 
	 {
		boolean result= adminService.checkAdmin(adminDashboard.getAdmin().getUserName(), adminDashboard.getAdmin().getPassword());
		if(result)
		{
			Fare fare= fareService.find(adminDashboard.getFare().getId());
			if(fare==null)
			{
				throw new DeleteFareException(fare.getId());
			}
			fleetService.delete(fare.getId());
			return ResponseEntity.ok("Fare details deleted successfully");
		}
		else
		{
			return ResponseEntity.ok("Admin details not found");
		}
	 }
	
	@PatchMapping("/admin/updatefare")
	public ResponseEntity<?> updateFare(@RequestBody AdminDashboard ad) throws UpdateFareException
	{
		boolean result= adminService.checkAdmin(ad.getAdmin().getUserName(), ad.getAdmin().getPassword());
		if(result)
		{
			Fare fare= fareService.find(ad.getFare().getId());
			if(fare==null)
			{
				throw new DeleteFareException(fare.getId());
			}
			fareService.modifyFare(ad.getFare());
			return ResponseEntity.ok("Fare details updated");
		}
		else
		{
			return ResponseEntity.ok("Admin details not found");
		}
	}
	
	@GetMapping("/admin/findallflight")
	public List<Flight> findFlight(@RequestBody AdminDashboard adminDashboard) 
	{
		boolean result = adminService.checkAdmin(adminDashboard.getAdmin().getUserName(),adminDashboard.getAdmin().getPassword());
		if(result)
		{
			return flightService.findAll();
		}
		return flightService.findAll();
	}
}
