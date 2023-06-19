package com.example.demoproject.service;

import java.util.List;

import com.example.demoproject.entitymodel.Designation;

public interface DesignationDataService {
	
boolean existsByDesignationName(String designationName);
	
	boolean existsByIdService(long id);
	
	Designation saveDesignationService(Designation reqDesignation);
	
	List<Designation> fetchAllDesignations();
	
	List<Designation> retrieveAllDesignations();
	
	Designation updateDesignationService(
			Designation reqDesignation, Long id);
	
	boolean deleteDesignationService(Long id);
	

}

