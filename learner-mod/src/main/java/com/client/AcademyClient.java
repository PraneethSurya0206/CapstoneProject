package com.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.exceptions.CustomException;
import com.model.Academy;

@Component
public class AcademyClient {
	   @Autowired
	    private RestTemplate restTemplate;

	  /* public List<Academy> getAcademiesBySportName(String sportName) {
			 ResponseEntity<Academy[]> response = restTemplate.getForEntity(
	            "http://academyMS/academies/sport/" + sportName,
	            Academy[].class
	        );
	        return Arrays.asList(response.getBody());
	    }*/
	   public List<Academy> getAcademiesBySportName(String sportName) throws CustomException {
		    ResponseEntity<Academy[]> response = restTemplate.getForEntity(
		        "http://academyMS/academies/sport/" + sportName,
		        Academy[].class
		    );

		    List<Academy> academies = Arrays.asList(response.getBody());

		    if (academies.isEmpty()) {
		        throw new CustomException("That sport is not in this academy.");
		    }

		    return academies;
		}


	  
	
}

