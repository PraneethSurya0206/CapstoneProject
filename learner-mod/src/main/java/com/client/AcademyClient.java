package com.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.model.Academy;

@Component
public class AcademyClient {
	   @Autowired
	    private RestTemplate restTemplate;

	   public List<Academy> getAcademiesBySportName(String sportName) {
			 ResponseEntity<Academy[]> response = restTemplate.getForEntity(
	            "http://academyMS/academies/sport/" + sportName,
	            Academy[].class
	        );
	        return Arrays.asList(response.getBody());
	    }

	  
	
}

