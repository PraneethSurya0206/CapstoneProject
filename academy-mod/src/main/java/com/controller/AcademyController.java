package com.controller;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exceptions.CustomException;
import com.model.Academy;
import com.service.AcademyService;

@RestController
@RequestMapping("/academies")
@Validated
public class AcademyController {
	
	@Autowired
	AcademyService aservice;
	private static final Logger logger = LoggerFactory.getLogger(AcademyController.class);
	
	//for academy details//
	@GetMapping("/{academyId}")
	public ResponseEntity<Academy> getAcademyById(@PathVariable("academyId") int academyId) throws CustomException {
       
        logger.info("Received request to get academy by ID: {}", academyId);

        try {
            Optional<Academy> academy = aservice.getAcademyById(academyId);
            if (academy.isPresent()) {
                logger.info("Academy found for ID: {}", academyId);
                return ResponseEntity.ok(academy.get());
            } else {
               logger.warn("No academy record found for ID: {}", academyId);
                throw new CustomException("No Academy record found by ID: " + academyId);
            }
           } catch (Exception e) {
              logger.error("Unexpected error occurred while retrieving academy for ID: {}", academyId, e);
               throw new CustomException("No Academy record found by ID: " + academyId);
           }
       }
        
	//for sportName //
	 @GetMapping("/sport/{sportName}")
	    public ResponseEntity<List<Academy>> getAcademiesBySportName(@PathVariable("sportName") String sportName) throws CustomException {
	        logger.info("Received request to get academies for sport: {}", sportName);
	        try {
	            List<Academy> academies = aservice.getAcademiesBySportName(sportName);
	            
	       if (academies.isEmpty()) {
	                logger.warn("No academies found for sport: {}", sportName);
	                throw new CustomException("Record of No Academy of Sport is found for " + sportName);
	            } else {
	                logger.info("Found {} academies for sport: {}", academies.size(), sportName);
	                return ResponseEntity.ok(academies);
	            }
	        } catch (CustomException e) {
	        	logger.error("Unexpected error occurred while retrieving academies for sport: {}", sportName, e);
	            throw new CustomException("Record of No Academy of Sport is found for " + sportName);
	        }
	    }
	        }
