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

import com.example.demoproject.entitymodel.Gender;
import com.example.demoproject.response.ResponseHandler;
import com.example.demoproject.service.GenderDataService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gender")
public class GenderController {
	
	private GenderDataService genderDataService;
	
	public GenderController(GenderDataService genderDataService) {
		this.genderDataService = genderDataService;
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Object> createGenderDetailsController(
			@RequestBody @Valid Gender reqGender){
		
		if (genderDataService.existsByGenderName(reqGender.getGenderName())==true) {
			return ResponseHandler.generateErrorResponse(
					"Record Already exists", HttpStatus.CONFLICT);
		}
		
		Gender data = genderDataService
				.createGenderDetailsService(reqGender);
		
		if (data!=null) {
			return ResponseHandler.generateResponse(
					"Success", HttpStatus.OK, data);
		} else {
			return ResponseHandler.generateErrorResponse(
					"Failed to create Gender", 
					HttpStatus.CONFLICT);
		}
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<Object> retrieveAllGenderDetailsController(){
		
		List<Gender> genderList = genderDataService
				.retrieveGenderdetailsAllService();
		
		if (genderList.isEmpty()) {
			return ResponseHandler.generateErrorResponse(
					"Gender List is Empty", HttpStatus.BAD_REQUEST);
		} else {
			return ResponseHandler.generateResponse(
					"Successfully updated Department",
					HttpStatus.OK,
					genderList);
		}
		 
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/all")
	public ResponseEntity<Object> fetchAllGenderDetailsController(){
		
		List<Gender> genderList = genderDataService
				.fetchGenderdetailsAllService();
		
		if (genderList.isEmpty()) {
			return ResponseHandler.generateErrorResponse(
					"Gender List is Empty", HttpStatus.BAD_REQUEST);
		} else {
			return ResponseHandler.generateResponse(
					"Successfully updated Department",
					HttpStatus.OK,
					genderList);
		}
		 
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateGenderDetailsController(
			@RequestBody @Valid Gender reqGender,
			@PathVariable @Valid Long id){
		
		if (genderDataService.existsByGenderName(
				reqGender.getGenderName())==true &&
				genderDataService.existsById(id)==true) {
			
			return ResponseHandler.generateErrorResponse(
					"Record Already Exists ", HttpStatus.CONFLICT);
			
		} else {
			
			try {
				
				Gender data = genderDataService.updateGenderByIdService(
						reqGender, id);
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
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable @Valid Long id){
		
		if (genderDataService.existsById(id)==false) {
			return ResponseHandler.generateErrorResponse(
					"Invalid ID", HttpStatus.CONFLICT);
		} else {
		genderDataService.deleteGenderById(id);
		return ResponseHandler.generateSuccessResponse(
				 "Gender Successfully deleted", HttpStatus.OK);
		}

	}
	

}

