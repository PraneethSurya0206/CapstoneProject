package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.LearnerDto;
import com.model.Academy;
import com.model.Enrollment;
import com.model.Learner;
import com.service.EnrollmentService;
import com.service.LearnerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/learners")
@Validated
public class LearnerController {
	
	@Autowired
	LearnerService lservice;
	
	@Autowired
	EnrollmentService eservice;
	
	
	

	String baseUrl = "http://academy/academies";
	 
	//Registration
	@PostMapping("/")
	public ResponseEntity<?> Register(@Valid @RequestBody Learner learner)
	{
		Learner register = lservice.addNewLearner(learner);
		return new ResponseEntity<>(register, HttpStatus.OK);
	}
	
	@PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@Valid @RequestBody LearnerDto learnerlogin) {
	 Learner learner2 =learnerlogin.toEnity();
        String customer = lservice.verify(learner2); //changed Learner to string
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Required Both email and password");
        }
    }
	
	@GetMapping("/sport/{sportName}")
    public ResponseEntity<List<Academy>> getAcademiesBySportName(@PathVariable("sportName") String sportName) {
		String url = baseUrl+ "/" + sportName;
        List<Academy> academies = lservice.getAcademiesBySportName(sportName);
        return new ResponseEntity<>(academies, HttpStatus.OK);
    }
	
	@PostMapping("/enroll")
	public ResponseEntity<Enrollment> enrollInAcademy(@PathVariable int learnerId, @PathVariable int academyId) {
	    Enrollment enroll = lservice.enrollInAcademy(learnerId, academyId);
	    return new ResponseEntity<>(enroll, HttpStatus.CREATED);
	}
	
}