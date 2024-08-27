package com.controller;


import java.util.List;
import java.util.Optional;
import com.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Academy;
import com.service.AcademyService;

@RestController
@RequestMapping("/academies")
@Validated
public class AcademyController {
	
	@Autowired
	AcademyService aservice;
	
	
	@GetMapping("/{academyId}")
		public ResponseEntity<Academy> getAcademyById(@PathVariable("academyId") int id) throws CustomException {
			Optional<Academy> academy = aservice.getAcademyById(id);
			if(academy.isPresent()) {
				return ResponseEntity.ok(academy.get());
			}else {
				throw new CustomException("Record of No Sport is found by id: " + id);
			}
	}
	
	@GetMapping("/sport/{sportName}")
	public ResponseEntity<List<Academy>>  getAcademiesBySportName(@PathVariable("sportName") String sportName) throws CustomException {
		List<Academy> academies= aservice.getAcademiesBySportName(sportName);
		if((academies.isEmpty())) {
			throw new CustomException("Record of No Academy of Sport is found for " + sportName);
			}else {
			return ResponseEntity.ok(academies); 
   
}
}}