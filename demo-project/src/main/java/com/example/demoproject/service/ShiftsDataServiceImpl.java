package com.example.demoproject.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demoproject.entitymodel.Shifts;
import com.example.demoproject.repository.ShiftsRepository;


@Service
public class ShiftsDataServiceImpl implements ShiftsDataService{

	@Autowired
	@Qualifier("shiftsRepository")
	private ShiftsRepository shiftsRepository;
	
	public ShiftsDataServiceImpl(ShiftsRepository repository) {
		this.shiftsRepository = repository;
	}
	
	
	public boolean existsByShiftName(String shiftName) {
		
		return shiftsRepository.existsByShiftName(shiftName);
	}

	@Override
	public boolean existsById(Long id) {

		return shiftsRepository.existsById(id);
	}

	@Override
	public Shifts createShiftsService(Shifts reqShift) {
		
		if (Objects.nonNull(reqShift)) {
			return shiftsRepository.save(reqShift);
		}
		
		return null;
	}

	@Override
	public List<Shifts> retrieveAllShiftsService() {
		
		if (shiftsRepository.findAll().isEmpty()) {
			return null;
		}
		
		List<Shifts> shiftList = shiftsRepository.findAll();
		return shiftList;

	}

	@Override
	public List<Shifts> fetchAllShiftsService() {
		
		if (shiftsRepository.findByStatus("Active")!=null) {
			List<Shifts> list = shiftsRepository.findByStatus("Active");
			return list;
		}
		
		return null;
	}

	@Override
	public Shifts updateShiftsByIdService(Shifts reqShift, Long id) {
		
		if (Objects.isNull(reqShift) || id==0) {
					
			return null;
		} 
			
		if (shiftsRepository.existsByShiftName(
				reqShift.getShiftName())==true &&
						shiftsRepository.existsById(id)==false) {
			
			return null;
			
		} else {
			
			Shifts shiftData = shiftsRepository.findById(id).get();
			
			shiftData.setShiftName(reqShift.getShiftName());
			shiftData.setStatus(reqShift.getStatus());
			
			shiftsRepository.save(shiftData);
			
			return shiftData;
		}
		
	}

	@Override
	public boolean deleteById(Long id) {
		
		if (shiftsRepository.existsById(id)==false) {
			return false;
		} else {
			Shifts shiftData = shiftsRepository.findById(id).get();
			
			shiftData.setStatus("Inactive");
			shiftsRepository.save(shiftData);
			
//			repository.deleteById(id);
			return true;
		}
		
	}
	
}
