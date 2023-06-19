package com.example.demoproject.service;

import java.util.List;

import com.example.demoproject.entitymodel.Gender;

public interface GenderDataService {
	
boolean existsByGenderName(String genderName);
	
	boolean existsById(Long id);
	
	Gender createGenderDetailsService(Gender reqGender);
	
	List<Gender> retrieveGenderdetailsAllService();
	
	List<Gender> fetchGenderdetailsAllService();
		
	Gender updateGenderByIdService(Gender reqGender,Long id);
		
	boolean deleteGenderById(Long id);

}

