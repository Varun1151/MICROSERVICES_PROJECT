package com.example.VaccinationService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.VaccinationService.Entity.VaccinationCenter;
import com.example.VaccinationService.Model.Citizen;
import com.example.VaccinationService.Model.RequiredResponse;
import com.example.VaccinationService.repositories.VaccinationRepo;



@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationController {
	
	@Autowired
	private VaccinationRepo centerRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping(path ="/add")
	public ResponseEntity<VaccinationCenter> addCitizen(@RequestBody VaccinationCenter vaccinationCenter) {
		VaccinationCenter vaccinationCenterAdded = centerRepo.save(vaccinationCenter);
		return new ResponseEntity<>(vaccinationCenterAdded, HttpStatus.OK);
	}
	
	@GetMapping(path="/all")
	public ResponseEntity<List<VaccinationCenter>> getAllVaccinationCenters(){
		List<VaccinationCenter> centers = centerRepo.findAll();
		return new ResponseEntity<>(centers, HttpStatus.OK);
	}


	@GetMapping(path = "/id/{id}")
	public ResponseEntity<RequiredResponse> getAllDataBasedonCenterId(@PathVariable Integer id){
		RequiredResponse requiredResponse =  new RequiredResponse();
		//1st get vaccination center detail
		VaccinationCenter center  = centerRepo.findById(id).get();
		requiredResponse.setCenter(center);
		
		// then get all citizen registerd to vaccination center
		
		java.util.List<Citizen> listOfCitizens = restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/vacc_id/"+id, List.class);
		requiredResponse.setCitizens(listOfCitizens);
		return new ResponseEntity<RequiredResponse>(requiredResponse, HttpStatus.OK);
	}
}