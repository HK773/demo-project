package com.example.demoproject.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demoproject.entitymodel.EmployeeType;
import com.example.demoproject.repository.EmployeeTypeRepository;

@Service
public class EmployeeTypeDataServiceImpl implements EmployeeTypeDataService{

	@Autowired
	@Qualifier("employeeTypeRepository")
	private EmployeeTypeRepository employeeTypeRepository;
	
	public EmployeeTypeDataServiceImpl(
			EmployeeTypeRepository employeeTypeRepository2) {
	this.employeeTypeRepository = employeeTypeRepository2;	
	}
	
	@Override
	public boolean existsByEmployeeType(String employeeTypeName) {
	
		return employeeTypeRepository.existsByEmployeeTypeName(employeeTypeName);
	}

	@Override
	public boolean existsById(Long employeeTypeId) {
		
		return employeeTypeRepository.existsById(employeeTypeId);
	}

	@Override
	public EmployeeType createEmployeeTypeService(EmployeeType reqEmployeeType) {
		
		if (Objects.nonNull(reqEmployeeType)) {
			EmployeeType employeeType = employeeTypeRepository.save(reqEmployeeType);
			return employeeType;
		}
		
		return null;
	}

	@Override
	public List<EmployeeType> fetchAllEmployeeTypeDetailsService() {
		
		if (employeeTypeRepository.findByStatus("Active")!=null) {
			return employeeTypeRepository.findByStatus("Active");	
		}
		
		return null;
	}

	@Override
	public List<EmployeeType> retrieveAllEmployeeTypeDetailsService() {
		
		if (employeeTypeRepository.findAll().isEmpty()) {
			return null;
		}
		
		List<EmployeeType> employeeTypeList = employeeTypeRepository.findAll();
		return employeeTypeList;
	}

	@Override
	public EmployeeType updateEmployeeTypeByIdService(
			EmployeeType reqEmployeeType, Long id) {
		

		if (Objects.isNull(reqEmployeeType) || id==0) {
			
			return null;
		}
		
		if (employeeTypeRepository.existsByEmployeeTypeName(
				reqEmployeeType.getEmployeeTypeName())==true &&
						employeeTypeRepository.existsById(id)==false) {
			
			return null;
		} else {
			
			EmployeeType employeeTypeData = employeeTypeRepository.findById(id).get();
			
			employeeTypeData.setEmployeeTypeName(reqEmployeeType.getEmployeeTypeName());
			employeeTypeData.setStatus(reqEmployeeType.getStatus());
			
			employeeTypeRepository.save(employeeTypeData);
			
			return employeeTypeData;
			
		}

	}

	@Override
	public boolean deleteEmployeetypeById(Long id) {
		
		if (employeeTypeRepository.existsById(id)==false) {
			return false;
		} else {
			EmployeeType employeeTypeData = employeeTypeRepository.findById(id).get();
			
			employeeTypeData.setStatus("Inactive");		
			employeeTypeRepository.save(employeeTypeData);
			
//			repository.deleteById(id);
			return true;
		}
		
		
	}



}

