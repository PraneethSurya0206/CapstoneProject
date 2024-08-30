package com.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.model.Academy;
import com.model.Enrollment;
import com.repository.EnrollmentRepository;
import com.repository.LearnerRepository;

import jakarta.validation.Valid;


@Service
public class EnrollmentService {
	
	@Autowired
	LearnerRepository lrepository;

	@Autowired
	EnrollmentRepository erepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	String baseUrl = "http://academyms/academies";

	// enroll//
	
	public void enroll(@Valid Enrollment enrollments) {
		
			System.out.println(enrollments.getAcademyId() +" "+" "+enrollments.getLearnerId());
			 Enrollment enrollment = new Enrollment();
			 enrollment.setAcademyId(enrollments.getAcademyId());
		     enrollment.setLearnerId(enrollments.getLearnerId());
		     enrollment.setEnrollmentDate((LocalDate) enrollments.getEnrollmentDate());
		        
		        
		        erepository.save(enrollment);  
		
	}
             
	
	// enrollmentid//
	
	
	public Optional<Enrollment> getEnrollmentById(int id) {
		// TODO Auto-generated method stub
		return erepository.findById(id);
	}


	 
}
	
