package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.client.AcademyClient;
import com.model.Academy;
import com.model.Enrollment;
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
	

	
	
	//Registration
	public Learner addNewLearner(Learner learner) {
		Learner learnersaved = lrepository.save(learner);
		return learner;
		
	}
	
	 //login
	public String verify(Learner learner2) {
		System.out.println("method called" + learner2.getEmail());
	    Learner credentials = lrepository.findByEmail(learner2.getEmail());
	    System.out.println("method called" + credentials);
	    if (credentials != null && credentials.getPassword().equals(learner2.getPassword())) {
	    	System.out.println("ok called");
	        return ("ALL THE DETAILS ARE MATCHED");
	    }
	    return null;
	}

	 public List<Academy> getAcademiesBySportName(String sportName) {
	        return academyClient.getAcademiesBySportName(sportName);
	    }

	public Enrollment enrollInAcademy(int learnerId, int academyId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}

			
	

