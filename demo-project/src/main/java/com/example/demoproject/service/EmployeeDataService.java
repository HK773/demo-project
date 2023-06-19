package com.example.demoproject.service;

import java.util.List;

import com.example.demoproject.dto.EmployeeDetailsDTO;
import com.example.demoproject.dto.EmployeeLoginDetailsDTO;
import com.example.demoproject.entitymodel.Employee;

import jakarta.validation.Valid;

public interface EmployeeDataService {
	
	boolean existsById(Long id);
	
	boolean existsByFirstName(String firstName);
	
	boolean existsByMailId(String mailId);
	
	Employee saveEmployeeService(Employee reqEmployee);

	List<Employee> retrieveAllEmployeeDetailsService();

	void sendMail(@Valid String mailId) ;
	
	String updateEmployeePasswordService(String username, String password);
		
	Employee retrieveEmployeeDetailsByIdService(Long id);
	
	Employee updateEmployeeDetailsService(Employee employee, 
			Long employeeId);
	
	boolean deleteEmployeeDetailService(Long id);

	List<EmployeeDetailsDTO> fetchEmployeeListService();

	String generateRandomPassword(String mailId);

	EmployeeLoginDetailsDTO fetchLoginDetails(String mailId);

	

	
		
	
	
//	List<EmployeeDetailsDTO> retrieveAdminListService(String roleName);
	
//	Employee assignRoleService(String roleName, Long employeeId);
	
}

