package com.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.exceptions.CustomException;
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
	private static final Logger logger = LoggerFactory.getLogger(LearnerController.class);
	//Registration
	
	@PostMapping("/")
	public ResponseEntity<?> Register(@Valid @RequestBody Learner learner) throws CustomException
	{
		Learner register = lservice.addNewLearner(learner);
		return new ResponseEntity<>(register, HttpStatus.OK);
	}
	
	//login
	@PostMapping("/login")
	public ResponseEntity<?> loginCustomer(@Valid @RequestBody LearnerDto learnerlogin) {
		Learner learners = learnerlogin.toEnity();
		String customer = lservice.verify(learners); 
		if (customer != null) {
			return ResponseEntity.ok(customer);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Give proper email and password");
		}
	}
	
	//by sport name
	@GetMapping("/sport/{sportName}")
    public ResponseEntity<List<Academy>> getAcademiesBySportName(@PathVariable("sportName") String sportName) throws CustomException {
		String url = baseUrl+ "/" + sportName;
        List<Academy> academies = lservice.getAcademiesBySportName(sportName);
        return new ResponseEntity<>(academies, HttpStatus.OK);
    }
	
	//enroll
	/*  @PostMapping("/enroll")
             public ResponseEntity<String> LearnerEnrollment(@Valid @RequestBody Enrollment enrollments) {
               Logger logger = LoggerFactory.getLogger(this.getClass());

            try {
               eservice.enrollment(enrollments);
                logger.info("Enrolled successfully");
                  return new ResponseEntity<>("Enrolled successfully", HttpStatus.CREATED);
                 } catch (Exception e) {
                     logger.error("Error while enrolling: {}", e.getMessage());
                 return new ResponseEntity<>("Error while enrolling: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                 }
                 }*/
	
	    
	//enrollmentid
	  @GetMapping("/{enrollmentId}")
		public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable("enrollmentId") int id) throws CustomException {
			Optional<Enrollment> enrollment = eservice.getEnrollmentById(id);
			if(enrollment.isPresent()) {
				return ResponseEntity.ok(enrollment.get());
			}else {
				throw new CustomException("Record of No Enrollment is found by id: " + id);
			}
	}
	  
	  
		 @GetMapping("/academy/{academyId}")
		  public ResponseEntity<?> searchAcademiesbyId(@PathVariable("academyId") int academyId) throws CustomException {
		        Academy academy = lservice.getAcademyById(academyId);
		        if (academy == null ) {
		            throw new CustomException("No records found for the AcademyId: " + academyId);
		        }
		        
		        return new ResponseEntity<>(academy, HttpStatus.OK);
		    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 //by academyid
		
		
	  
	  
		/*@GetMapping(("/academy/{academyId}"))
	    public ResponseEntity<List<Academy>> getAcademyById(@PathVariable("academyId") int academyId) {
			String url = baseUrl+ "/" + academyId;
	        List<Academy> academies =  lservice.getAcademyId(academyId);
	        return new ResponseEntity<>(academies, HttpStatus.OK);
	    }*/
	
	}
 
       
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
