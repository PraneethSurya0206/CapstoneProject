package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Academy;
import com.repository.AcademyRepository;

@Service
public class AcademyService {
	
	@Autowired
	AcademyRepository arepository;

	public Optional<Academy> getAcademyById(int id) {
		// TODO Auto-generated method stub
		return arepository.findById(id) ;
	}

	

	public List<Academy> getAcademiesBySportName(String sportName) {
		// TODO Auto-generated method stub
		return arepository.findAcademyBySportName(sportName);
	}

	

	
}
	


