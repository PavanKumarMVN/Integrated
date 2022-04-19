package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.GuestUserService;
import com.dao.LocationService;
import com.model.AdminDashboard;
import com.model.Flight;
import com.model.Location;

@RestController
public class GuestUserRestController {

	@Autowired
	LocationService locationService;
	
	@Autowired
	GuestUserService guestService;
	
	@GetMapping("/guest/findalllocation")
	public List<Location> findLocation(@RequestBody AdminDashboard adminDashboard) 
	{
		boolean result = guestService.checkGuestUser(adminDashboard.getGuestUser().getUserName(),adminDashboard.getGuestUser().getPassword());
		if(result)
		{
			return locationService.findAll();
		}
		return locationService.findAll();
	}
	
	@PostMapping("/guest/addlocation")
	public ResponseEntity<?> addFlight(@RequestBody AdminDashboard adminDashboard) throws AddLocationException
	{
		boolean result= guestService.checkGuestUser(adminDashboard.getGuestUser().getUserName(), adminDashboard.getGuestUser().getPassword());
		
		if(result) {
			if(adminDashboard.getGuestUser().getUserName()==null || adminDashboard.getGuestUser().getPassword()==null)
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

	   
	@DeleteMapping("/guest/deletelocation")
	 public ResponseEntity<?> deleteLocation(@RequestBody AdminDashboard adminDashboard) throws DeleteLocationException 
	 {
		boolean result= guestService.checkGuestUser(adminDashboard.getAdmin().getUserName(), adminDashboard.getAdmin().getPassword());
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
	@PatchMapping("/guest/location")
	public ResponseEntity<?> updateLocation(@RequestBody AdminDashboard ad) throws UpdateLocationException
	{
		boolean result= guestService.checkGuestUser(ad.getGuestUser().getUserName(), ad.getGuestUser().getPassword());
		if(result)
		{
			Location location= locationService.find(ad.getLocation().getId());
			if(location==null)
			{
				throw new UpdateLocationException();
			}
			locationService.update(ad.getLocation());
			return ResponseEntity.ok("location details updated");
		}
		else
		{
			return ResponseEntity.ok("Admin details not found");
		}
	}
}

