package com.example.demoproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demoproject.entitymodel.MartialStatus;
import com.example.demoproject.repository.MartialStatusRepository;

@Service
public class MartialStatusDataServiceImpl implements MartialStatusDataService{

	@Autowired
	@Qualifier("martialStatusRepository")
	private MartialStatusRepository martialStatusRepository;
	
	@Override
	public MartialStatus createMartialStatusService(
			MartialStatus reqMartialStatus) {
		 
		MartialStatus martialStatus = martialStatusRepository.save(reqMartialStatus);
		
		return martialStatus;
	}

	@Override
	public List<MartialStatus> retrieveAllMartialStatusService() {
		List<MartialStatus> martialStatusList = martialStatusRepository.findAll();
		return martialStatusList;
	}

	@Override
	public MartialStatus updateMartialStatusByIdService(
			MartialStatus req, Long id) {
		
		MartialStatus martialStatusData = martialStatusRepository.findById(id).get();
		
		martialStatusData.setMartialStatusName(req.getMartialStatusName());
		martialStatusData.setStatus(req.getStatus());
		
		martialStatusRepository.save(martialStatusData);
		return martialStatusData;
	}

	@Override
	public void deleteMartialStatusById(Long id) {
		
		MartialStatus martialStatusData = martialStatusRepository.findById(id).get();
		
		martialStatusData.setStatus("Inactive");		
		martialStatusRepository.save(martialStatusData);
		
		martialStatusRepository.deleteById(id);
	}

	@Override
	public boolean existsByMartialStatusName(String martialStatusName) {
		
		return martialStatusRepository.existsByMartialStatusName(martialStatusName);
	}

	@Override
	public boolean existsById(Long id) {
		
		return martialStatusRepository.existsById(id);
	}

}

