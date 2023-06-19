package com.example.demoproject.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoproject.entitymodel.Department;
import com.example.demoproject.response.ResponseHandler;
import com.example.demoproject.service.DepartmentDataService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	private DepartmentDataService departmentDataService;
	
	DepartmentController(DepartmentDataService departmentDataService){
		this.departmentDataService =departmentDataService;
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Object> saveDepartmentController(
			@Valid @RequestBody Department reqDepartment){
		
		if(departmentDataService.existsByDepartmentName(
				reqDepartment.getDepartmentName()) == true) {
			return ResponseHandler.generateErrorResponse(
					"Record Already exists", HttpStatus.CONFLICT);
		} 
		
				Department department = departmentDataService
						.saveDepartmentService(reqDepartment);	
				
				if (department!=null) {
					return ResponseHandler.generateResponse(
							"Successfully created Department", 
							HttpStatus.CREATED, department);
				} else {
					return ResponseHandler.generateErrorResponse(
							"Failed to create Department", 
							HttpStatus.CONFLICT);
				}
			
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/demo")
	public ResponseEntity<Object> fetchAllDepartmentController(){
		
		List<Department> departmentList = departmentDataService
							.fetchAllDepartmentService();
		
		if (departmentList.isEmpty()) {
			return ResponseHandler.generateErrorResponse(
					"List is Empty", HttpStatus.BAD_REQUEST);
		} else {
			return ResponseHandler.generateResponse(
					"Successfully updated Department",
					HttpStatus.OK,
					departmentList);
		}
			
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<Object> fetchAllDepartmentDetailsController(){
	
		List<Department> departmentList = departmentDataService
							.retrieveAllDepartmentDetailsService();
		
		if (departmentList.isEmpty()) {
			return ResponseHandler.generateErrorResponse(
					"List is Empty", HttpStatus.BAD_REQUEST);
		} else {
			return ResponseHandler.generateResponse(
					"Successfully fetched Department",
					HttpStatus.OK,
					departmentList);
		}
		
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateDepartmentController(
			@RequestBody @Valid Department  reqDepartment ,
			@PathVariable @Valid Long id){	
		
		if ((departmentDataService.existsByDepartmentName(
				reqDepartment.getDepartmentName())==true) &&
				(departmentDataService.existsById(id))==true){
			
			return ResponseHandler.generateErrorResponse(
					"Record Already Exists ", HttpStatus.CONFLICT);
			
		} else {
			try {
				Department data = departmentDataService
									.updateDepartmentById(
											reqDepartment, id);		
				if (data!= null) {
					return ResponseHandler.generateResponse(
							"Successfully updated Department", 
							HttpStatus.OK, data);
				} else {
					return ResponseHandler.generateErrorResponse(
							"Invalid ID ", HttpStatus.CONFLICT);
				}
							
			} catch (DataIntegrityViolationException e) {
				
				e.printStackTrace();
				
				return ResponseHandler.generateErrorResponse(
						e.getLocalizedMessage().toString(), 
						HttpStatus.CONFLICT);
			}
		}
		
	} 
		
//	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDepartmentById(
			@PathVariable @Valid Long id) {
						
		boolean data = departmentDataService.deleteDepartmentById(id);
		
		if (data) {
			 return ResponseHandler.generateSuccessResponse(
					 "Department Successfully deleted", HttpStatus.OK);
		} else {
			return ResponseHandler.generateErrorResponse(
					"Invalid ID ", HttpStatus.CONFLICT);
		}
			 

	}
		
}

