package com.example.demoproject.service;

import java.util.List;

import com.example.demoproject.entitymodel.EmployeeType;

public interface EmployeeTypeDataService {
	
boolean existsByEmployeeType(String employeeTypeName);
	
	boolean existsById(Long employeeTypeId);
	
	EmployeeType createEmployeeTypeService(EmployeeType reqEmployeeType);
	
	List<EmployeeType> fetchAllEmployeeTypeDetailsService();
	
	List<EmployeeType> retrieveAllEmployeeTypeDetailsService();
	
	EmployeeType updateEmployeeTypeByIdService(
			EmployeeType reqEmployeeType,
			Long id);
	
	boolean deleteEmployeetypeById(Long id);
	
}

