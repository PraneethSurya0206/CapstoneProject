package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.client.AcademyClient;
import com.exceptions.CustomException;
import com.model.Academy;
import com.model.Learner;
import com.repository.LearnerRepository;


@Service
public class LearnerService {
	
	@Autowired
	LearnerRepository lrepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	 @Autowired
     AcademyClient academyClient;
	

	 String baseUrl = "http://academyms/academies";
	 
	 
	
	 
	 public Learner addNewLearner(Learner learner) throws CustomException {
		    try {
		        Learner learnersaved = lrepository.save(learner);
		        return learnersaved;
		    } catch (Exception e) {
		        throw new CustomException("Registration failed. Please try again.");
		    }
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

	 public List<Academy> getAcademiesBySportName(String sportName) throws CustomException {
	        return academyClient.getAcademiesBySportName(sportName);
	    }



	 public Academy getAcademyById(int id) {
		 System.out.println(id);
			String url = baseUrl + "/" + id;
			System.out.println(url);
			return restTemplate.getForObject(url, Academy.class);
		}

	
}

	



			
	

