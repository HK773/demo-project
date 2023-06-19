package com.example.demoproject.service;

import java.util.List;

import com.example.demoproject.entitymodel.Department;

public interface DepartmentDataService {
	
	boolean existsByDepartmentName(String departmentName);
	
	boolean existsById(Long id);
	
	Department saveDepartmentService(Department reqDepartment);
	
	List<Department> fetchAllDepartmentService();
	
	List<Department> retrieveAllDepartmentDetailsService();
	
	Department updateDepartmentById(Department reqDepartment, Long id);
	
	boolean deleteDepartmentById(Long id);

}
