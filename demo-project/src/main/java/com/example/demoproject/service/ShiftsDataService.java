package com.example.demoproject.service;

import java.util.List;

import com.example.demoproject.entitymodel.Shifts;

public interface ShiftsDataService {
	
boolean existsByShiftName(String shiftName);
	
	boolean existsById(Long id);
	
	Shifts createShiftsService(Shifts reqShift);
	
	List<Shifts> retrieveAllShiftsService();
	
	List<Shifts> fetchAllShiftsService();
		
	Shifts updateShiftsByIdService(Shifts reqShift,Long id);
		
	boolean deleteById(Long id);



}

