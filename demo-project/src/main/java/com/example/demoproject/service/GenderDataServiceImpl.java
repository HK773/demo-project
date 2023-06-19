package com.example.demoproject.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demoproject.entitymodel.Gender;
import com.example.demoproject.repository.GenderRepository;


@Service
public class GenderDataServiceImpl implements GenderDataService {
	
	@Autowired
	@Qualifier("genderRepository")
	private GenderRepository genderRepository;
	
	public GenderDataServiceImpl(GenderRepository genderRepository) {
		this.genderRepository = genderRepository;
	}
	
	@Override
	public boolean existsByGenderName(String genderName) {
		
		return genderRepository.existsByGenderName(genderName);
	}

	@Override
	public boolean existsById(Long id) {
		
		return genderRepository.existsById(id);
	}

	@Override
	public Gender createGenderDetailsService(Gender reqGender) {
		
		if (Objects.nonNull(reqGender)) {
			Gender gender = genderRepository.save(reqGender);
			return gender;
		}
		
		return null;
	}

	@Override
	public List<Gender> retrieveGenderdetailsAllService() {
		
		if (genderRepository.findAll().isEmpty()) {
			return null;
		}
		List<Gender> genderList = genderRepository.findAll();
		return genderList;
		
	}

	@Override
	public List<Gender> fetchGenderdetailsAllService() {
		
		if (genderRepository.findByStatus("Active")!=null) {
			return genderRepository.findByStatus("Active");
		}
		
		return null;
	}

	@Override
	public Gender updateGenderByIdService(Gender reqGender, Long id) {
		
		if (Objects.isNull(reqGender) || id==0) {
			
			return null;
		}
		
		if (genderRepository.existsByGenderName(
				reqGender.getGenderName())==true && 
						genderRepository.existsById(id)==false) {
			return null;
		} else {
			Gender genderData = genderRepository.findById(id).get();
			
			genderData.setGenderName(reqGender.getGenderName());
			genderData.setStatus(reqGender.getStatus());
			
			genderRepository.save(genderData);
			
			return genderData;
		}
	}

	@Override
	public boolean deleteGenderById(Long id) {
		
		if (genderRepository.existsById(id)==false) {
			return false;
		} else {
			Gender genderData = genderRepository.findById(id).get();
			genderData.setStatus("Inactive");
			genderRepository.save(genderData);
			
			genderRepository.deleteById(id);
			
			return true;
		}
		
		
	}

}

