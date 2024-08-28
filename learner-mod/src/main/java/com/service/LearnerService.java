package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.client.AcademyClient;
import com.model.Academy;
import com.model.Enrollment;
import com.model.Learner;
import com.repository.LearnerRepository;

import jakarta.validation.Valid;


@Service
public class LearnerService {
	
	@Autowired
	LearnerRepository lrepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	 @Autowired
     AcademyClient academyClient;
	

	 String baseUrl = "http://academyms/academies";
	 
	 
	//Registration
	public Learner addNewLearner(Learner learner) {
		Learner learnersaved = lrepository.save(learner);
		return learner;
		
	}
	
	 //login
	public String verify(Learner learner) {
		System.out.println("method called" + learner.getEmail());
		Learner credentials = lrepository.findByEmail(learner.getEmail());
		System.out.println("method called" + credentials);
		if (credentials != null && credentials.getPassword().equals(learner.getPassword())) {
			System.out.println("ok called");
			return ("You are Authenticated Successfully");
		}
		return null;
	    
	}
	
	// by sportname//

	 public List<Academy> getAcademiesBySportName(String sportName) {
	        return academyClient.getAcademiesBySportName(sportName);
	    }

	//public List<Academy> getAcademyId(int academyId) {
		// TODO Auto-generated method stub
		//return null;
	 
	/* public Academy getAcademyId(int academyId) {
		    
		    Optional<Academy> academyOptional = Optional.empty();
		    return academyOptional.orElse(null); 
		}
	}*/


	 public Academy getAcademyById(int id) {
		 System.out.println(id);
			String url = baseUrl + "/" + id;
			System.out.println(url);
			return restTemplate.getForObject(url, Academy.class);
		}

	/*public List<Academy> getAcademyId(int academyId) {
		// TODO Auto-generated method stub
		return null;
		
	}*/

	
}

	



			
	

