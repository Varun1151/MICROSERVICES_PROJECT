package com.example.CitizenServer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.example.CitizenServer.Entity.Citizen;
import com.example.CitizenServer.repositories.CitizenRepo;

@RestController
@RequestMapping("/citizen")
public class CitizenController {
	
	@Autowired
	private CitizenRepo repo; 
	
	@RequestMapping(path ="/id/{id}")
	public ResponseEntity<Citizen> getById(@PathVariable Integer id) {
		
		Citizen citizen = repo.findById(id).orElse(null);
		return new ResponseEntity<>(citizen, HttpStatus.OK);
	}
	@RequestMapping(path="/vacc_id/{id}")
	public ResponseEntity<java.util.List<Citizen>> getByCenterId(@PathVariable Integer id) {
		
		List<Citizen> listCitizen = repo.findByVaccinationCenterId(id);
		return new ResponseEntity<>(listCitizen, HttpStatus.OK);
	}
	
	@PostMapping(path ="/add")
	public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen newCitizen) {
		
		Citizen citizen = repo.save(newCitizen);
		return new ResponseEntity<>(citizen, HttpStatus.OK);
	}	
	@DeleteMapping(path="/delete/{id}")
	public String deleteCitizen(@PathVariable Integer id){
		repo.deleteById(id);
		return "Citizen with id = "+id+" deleted";
	}
}