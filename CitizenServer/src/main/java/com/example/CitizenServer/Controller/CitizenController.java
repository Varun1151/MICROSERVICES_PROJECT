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
	
	@PostMapping(path ="/add")
	public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen newCitizen) {
		
		Citizen citizen = repo.save(newCitizen);
		return new ResponseEntity<>(citizen, HttpStatus.OK);
	}	
}