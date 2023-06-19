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

import com.example.demoproject.entitymodel.Designation;
import com.example.demoproject.response.ResponseHandler;
import com.example.demoproject.service.DesignationDataService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/designation")
public class DesignationController {
	
	private DesignationDataService designationDataService;
	
	public DesignationController(DesignationDataService designationDataService) {
		this.designationDataService = designationDataService;
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Object> createDesignationController(
			@RequestBody @Valid Designation reqDesignation){
		
	
		if (designationDataService.existsByDesignationName(
				reqDesignation.getDesignationName())==true) {
			return ResponseHandler.generateErrorResponse(
					"Record Already Exists", HttpStatus.CONFLICT);
		}
			Designation data = designationDataService
					.saveDesignationService(
					reqDesignation);
			if (data!=null) {
				return ResponseHandler.generateResponse(
						"Success", HttpStatus.OK, data);
			} else {
				return ResponseHandler.generateErrorResponse(
						"Failed to create Department", 
						HttpStatus.CONFLICT);
			}
		
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<Object> retrieveAllDesignationsController(){
		
		List<Designation> designationList = designationDataService
											.retrieveAllDesignations();
		
		if (designationList.isEmpty()) {
			return ResponseHandler.generateErrorResponse(
					"List is Empty", HttpStatus.BAD_REQUEST);
		} else {
			return ResponseHandler.generateResponse(
					"Successfully display designation",
					HttpStatus.OK,
					designationList);
		}
		
		
	}
	
	@GetMapping("demo")
	public ResponseEntity<Object> fetchAllDesignationsController(){
		
		List<Designation> designationList = designationDataService
											.fetchAllDesignations();
		
		if (designationList.isEmpty()) {
			return ResponseHandler.generateErrorResponse(
					"List is Empty", HttpStatus.BAD_REQUEST);
		} else {
			return ResponseHandler.generateResponse(
					"Successfully display designations",
					HttpStatus.OK,
					designationList);
		}
		
		
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("{id}")
	public ResponseEntity<Object> updateDesignationController(
			@RequestBody @Valid Designation reqDesignation,
			@PathVariable @Valid Long id){
		
			
	if (designationDataService.existsByDesignationName(
			reqDesignation.getDesignationName())==true &&
			designationDataService.existsByIdService(id)==true) {
		return ResponseHandler.generateErrorResponse(
				"Record Already Exists", HttpStatus.CONFLICT);
	} else {
		try {
			Designation data = designationDataService.updateDesignationService(
					reqDesignation, id);
			if (data!=null) {
				return ResponseHandler.generateResponse(
						"Success", HttpStatus.OK, data);
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
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deleteDesignationById(
			@PathVariable @Valid Long id) {

		boolean data = designationDataService.deleteDesignationService(id);
		
		if (data) {
			 return ResponseHandler.generateSuccessResponse(
					 "Designation Successfully deleted", HttpStatus.OK);
		} else {
			return ResponseHandler.generateErrorResponse(
					"Invalid ID ", HttpStatus.CONFLICT);
		}

	}
	

}

