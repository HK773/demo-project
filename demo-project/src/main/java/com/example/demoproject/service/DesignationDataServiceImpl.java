package com.example.demoproject.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoproject.entitymodel.Designation;
import com.example.demoproject.repository.DesignationRepository;


@Service
public class DesignationDataServiceImpl implements DesignationDataService {

	@Autowired
	private DesignationRepository designationRepository;
	
	public DesignationDataServiceImpl(DesignationRepository designationRepository2) {
		this.designationRepository = designationRepository2;
	}

	@Override
	public boolean existsByDesignationName(String designationName) {
		
		return designationRepository.existsByDesignationName(designationName);
	}

	@Override
	public boolean existsByIdService(long id) {
		
		return designationRepository.existsById(id);
	}

	@Override
	public Designation saveDesignationService(Designation reqDesignation) {
		
		if (Objects.nonNull(reqDesignation)) {
			Designation designation = designationRepository
										.save(reqDesignation);
			return designation;
		}
		
		return null;
	}

	@Override
	public List<Designation> retrieveAllDesignations() {
		if (designationRepository.findAll().isEmpty()) {
			return null;
		}
		
		return designationRepository.findAll();
		
	}
	
	@Override
	public List<Designation> fetchAllDesignations(){
		if (designationRepository.findByStatus("Active")!=null)
		{
			return designationRepository.findByStatus("Active");
		}
		return null;
	}

	@Override
	public Designation updateDesignationService(Designation reqDesignation, Long id) {
		
		if (Objects.isNull(reqDesignation) || id==0) {	
			return null;
		}
		
		if(designationRepository.existsByDesignationName(
				reqDesignation.getDesignationName())==true &&
			designationRepository.existsById(id)==false) {
			return null;
		} else {
		
		Designation designationData = designationRepository
				.findById(id).get();

		designationData.setDesignationName(reqDesignation.getDesignationName());
		designationData.setStatus(reqDesignation.getStatus());
		
		designationRepository.save(designationData);
		
		return designationData;
		}
	}

	@Override
	public boolean deleteDesignationService(Long id) {
		
		if (designationRepository.existsById(id)==false) {
			return false;
		} else {
			Designation designationData = designationRepository
					.findById(id).get();
			
			designationData.setStatus("Inactive");	
			designationRepository.save(designationData);
			
//			designationRepository.deleteById(id);
			return true;
		}
		
	}

}

