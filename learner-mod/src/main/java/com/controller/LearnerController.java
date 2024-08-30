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
    public ResponseEntity<?> Register(@Valid @RequestBody Learner learner) {
        logger.info("Register method called with learner: {}", learner);
         try {
            Learner register = lservice.addNewLearner(learner);
            logger.info("Learner successfully registered: {}", register);
            return new ResponseEntity<>(register, HttpStatus.OK);
        } catch (CustomException e) {
            logger.error("Error registering learner: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	
	//login
	 @PostMapping("/login")
	 public ResponseEntity<?> loginCustomer(@Valid @RequestBody LearnerDto learnerlogin) {
	         logger.info("Login attempt received for user: {}", learnerlogin.getEmail());
	         try {
	            Learner learners = learnerlogin.toEnity();
	            logger.debug("Converted DTO to entity: {}", learners);
	            String customer = lservice.verify(learners);
	            if (customer != null)
	            {	
	            	logger.info("Login successful for user: {}", learnerlogin.getEmail());
	                return ResponseEntity.ok(customer);
	            } else {
	            	logger.warn("Login failed for user: {}", learnerlogin.getEmail());
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
	            }
	        } catch (Exception e) {
	            
	            logger.error("Exception occurred during login attempt for user: {}", learnerlogin.getEmail(), e);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
	        }
	    }
	
	
	// get sportName from learners//
	 @GetMapping("/sport/{sportName}")
	 public ResponseEntity<List<Academy>> getAcademiesBySportName(@PathVariable("sportName") String sportName) throws CustomException {
	        
	        logger.info("Received request to get academies for sport: {}", sportName);
	        String url = baseUrl + "/sport/" + sportName;
	        logger.debug("Constructed URL for service call: {}", url);
	        try {
	            List<Academy> academies = lservice.getAcademiesBySportName(sportName);
	             logger.info("Retrieved {} academies for sport: {}", academies.size(), sportName);
	            return new ResponseEntity<>(academies, HttpStatus.OK);
	        } catch (CustomException e) {
	        	 logger.error("Error retrieving academies for sport: {}", sportName, e);
	            throw e; 
	        }
	    }
	        
	 
	 
	 //enroll
	 @PostMapping("/enroll")
             public ResponseEntity<String> LearnerEnrollment(@Valid @RequestBody Enrollment enrollments) {
               Logger logger = LoggerFactory.getLogger(this.getClass());

            try {
               eservice.enroll(enrollments);
                logger.info("Enrolled successfully");
                  return new ResponseEntity<>("Enrolled successfully", HttpStatus.CREATED);
                 } catch (Exception e) {
                     logger.error("Error while enrolling: {}", e.getMessage());
                 return new ResponseEntity<>("Error while enrolling: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                 }
                 }
	
	    
	// get enrollment id
	 
	  @GetMapping("/{enrollmentId}")
	  public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable("enrollmentId") int enrollmentId) throws CustomException
	  {
	     logger.info("Received request to get enrollment by ID: {}", enrollmentId);
	     try {
	            Optional<Enrollment> enrollment = eservice.getEnrollmentById(enrollmentId);
	            if (enrollment.isPresent())
	            {
	                
	             logger.info("Enrollment found for ID: {}", enrollmentId);
	                return ResponseEntity.ok(enrollment.get());
	            } else {
	                logger.warn("No enrollment record found for ID: {}", enrollmentId);
	                throw new CustomException("No Enrollment record found by ID: " + enrollmentId);
	            }
	        
	        } catch (Exception e) {
	            
	            logger.error("Unexpected error occurred while retrieving enrollment for ID: {}", enrollmentId, e);
	            throw new CustomException("No Enrollment record found by ID: " + enrollmentId);
	        }
	    }
	  
	 
	 
	   @GetMapping("/academy/{academyId}")
	   public ResponseEntity<?> searchAcademiesbyId(@PathVariable("academyId") int academyId) throws CustomException {
	        
	        logger.info("Received request to search for academy by ID: {}", academyId);

	        try {
	            
	            Academy academy = lservice.getAcademyById(academyId);
	             if (academy == null) {
	                logger.warn("No academy found with ID: {}", academyId);
	                throw new CustomException("No records found for the AcademyId: " + academyId);
	            }
	            
	            logger.info("Academy found with ID: {}", academyId);
	            return new ResponseEntity<>(academy, HttpStatus.OK);
	         } catch (Exception e) {
	             logger.error("Unexpected error occurred while retrieving academy with ID: {}", academyId, e);
	             throw new CustomException("No records found for the AcademyId: " + academyId);
	         }
	     }
	        }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 //by academyid
		
		
	  
	  
		/*@GetMapping(("/academy/{academyId}"))
	    public ResponseEntity<List<Academy>> getAcademyById(@PathVariable("academyId") int academyId) {
			String url = baseUrl+ "/" + academyId;
	        List<Academy> academies =  lservice.getAcademyId(academyId);
	        return new ResponseEntity<>(academies, HttpStatus.OK);
	    }*/
	

 
       
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
