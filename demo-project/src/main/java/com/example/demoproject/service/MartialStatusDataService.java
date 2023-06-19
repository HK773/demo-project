package com.example.demoproject.service;

import java.util.List;

import com.example.demoproject.entitymodel.MartialStatus;

public interface MartialStatusDataService {
	
	boolean existsByMartialStatusName(String martialStatusName);
	
	boolean existsById(Long id);

	MartialStatus createMartialStatusService(MartialStatus reqMartialStatus);
	
	List<MartialStatus> retrieveAllMartialStatusService();
		
	MartialStatus updateMartialStatusByIdService(MartialStatus reqmartialStatus,Long id);
		
	void deleteMartialStatusById(Long id);

		
}

