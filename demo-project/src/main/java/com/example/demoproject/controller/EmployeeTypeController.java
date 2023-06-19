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

import com.example.demoproject.entitymodel.EmployeeType;
import com.example.demoproject.response.ResponseHandler;
import com.example.demoproject.service.EmployeeTypeDataService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employeetype")
public class EmployeeTypeController {
	
	private EmployeeTypeDataService employeeTypeDataService;
	
	public EmployeeTypeController(EmployeeTypeDataService employeeTypeDataService) {
		this.employeeTypeDataService = employeeTypeDataService;
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Object> createEmployeeTypeController(
			@RequestBody @Valid EmployeeType reqEmployeeType){
		
		if (employeeTypeDataService.existsByEmployeeType(
				reqEmployeeType.getEmployeeTypeName())==true) {
			return ResponseHandler.generateErrorResponse(
					"Record Already exists", HttpStatus.CONFLICT);
		}
		
		EmployeeType data = employeeTypeDataService
				.createEmployeeTypeService(reqEmployeeType);
		
		if (data!=null) {
			return ResponseHandler.generateResponse(
					"Success", HttpStatus.OK, data);
		}else {
			return ResponseHandler.generateErrorResponse(
					"Failed to create Department", 
					HttpStatus.CONFLICT);
		}
		
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<Object> retrieveAllEmployeeTypeController(){
		
		List<EmployeeType> employeeTypeList = employeeTypeDataService
									.retrieveAllEmployeeTypeDetailsService();
		if (employeeTypeList.isEmpty()) {
			return ResponseHandler.generateErrorResponse(
					"employeeTypeList is Empty", HttpStatus.BAD_REQUEST);
		} else {
			return ResponseHandler.generateResponse(
					"Successfully updated Department",
					HttpStatus.OK,
					employeeTypeList);
		}
		 
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/all")
	public ResponseEntity<Object> fetchAllEmployeeTypeController(){
		
		List<EmployeeType> employeeTypeList = employeeTypeDataService
									.fetchAllEmployeeTypeDetailsService();
		if (employeeTypeList.isEmpty()) {
			return ResponseHandler.generateErrorResponse(
					"employeeTypeList is Empty", HttpStatus.BAD_REQUEST);
		} else {
			return ResponseHandler.generateResponse(
					"Successfully updated Department",
					HttpStatus.OK,
					employeeTypeList);
		}
		 
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateEmployeeTypeController(
			@RequestBody @Valid EmployeeType reqEmployeeType,
			@PathVariable @Valid Long id){
		
		if (employeeTypeDataService.existsByEmployeeType(
				reqEmployeeType.getEmployeeTypeName())==true &&
				employeeTypeDataService.existsById(id)==true) {
			
			return ResponseHandler.generateErrorResponse(
					"Record Already Exists ", HttpStatus.CONFLICT);
			
		} else {
			
			try {
				EmployeeType data = employeeTypeDataService
						.updateEmployeeTypeByIdService(reqEmployeeType, id);
				
				if (data!= null) {
					return ResponseHandler.generateResponse(
							"Successfully updated EmployeeType", 
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
	public ResponseEntity<Object> deleteEmployeeTypeController(
			@PathVariable @Valid Long id){
		
		boolean data = employeeTypeDataService.deleteEmployeetypeById(id);
		
		if (data) {
			return ResponseHandler.generateSuccessResponse(
					 "EmployeType Successfully deleted", HttpStatus.OK);
		} else {
			return ResponseHandler.generateErrorResponse(
					"Invalid ID ", HttpStatus.CONFLICT);
		}

	}
	


}

