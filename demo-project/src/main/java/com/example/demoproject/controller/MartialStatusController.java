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

import com.example.demoproject.entitymodel.MartialStatus;
import com.example.demoproject.response.ResponseHandler;
import com.example.demoproject.service.MartialStatusDataService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/martial-status")
public class MartialStatusController {
	
	private MartialStatusDataService martialStatusDataService;
	
	public MartialStatusController(
			MartialStatusDataService martialStatusDataService) {
		
		this.martialStatusDataService = martialStatusDataService;
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Object> createMartialStatusController(
			@RequestBody @Valid MartialStatus reqMartialStatus){
	
		if (martialStatusDataService.existsByMartialStatusName(
				reqMartialStatus.getMartialStatusName())==true) {
			return ResponseHandler.generateErrorResponse(
					"Record Already exists", HttpStatus.CONFLICT);
		} 
		
		MartialStatus data = martialStatusDataService
				.createMartialStatusService(reqMartialStatus);
		
		if (data!=null) {
			return ResponseHandler.generateResponse(
					"Success", HttpStatus.OK, data);
		} else {
			return ResponseHandler.generateErrorResponse(
					"Failed to create MartialStatus", 
					HttpStatus.CONFLICT);
		}
		
		
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<Object> fetchAllMartialStatusController(){
		
		List<MartialStatus> martialStatusList = martialStatusDataService
				.retrieveAllMartialStatusService();
		
		if (martialStatusList.isEmpty()) {
			return ResponseHandler.generateErrorResponse(
					"MartialStatus List is Empty", HttpStatus.BAD_REQUEST);
		}else {
			return ResponseHandler.generateResponse(
					"Successfully updated Department",
					HttpStatus.OK,
					martialStatusList);
		}
		 
	}

//	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(
			@RequestBody @Valid MartialStatus reqMartialStatus,
			@PathVariable @Valid Long id){
		
		if (martialStatusDataService.existsByMartialStatusName(
				reqMartialStatus.getMartialStatusName())==true &&
				martialStatusDataService.existsById(id)==true) {
			return ResponseHandler.generateErrorResponse(
					"Record Already exists", HttpStatus.CONFLICT);
		} else {
			
			try {
				
				MartialStatus data = martialStatusDataService
						.updateMartialStatusByIdService(reqMartialStatus, id);
				
				if (data!=null) {
					return ResponseHandler.generateResponse(
							"Success", HttpStatus.OK, data);
				} else {
					return ResponseHandler.generateErrorResponse(
							"Failed to update MartialStatus", 
							HttpStatus.CONFLICT);
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
	public ResponseEntity<Object> deleteMartialStatusController(
			@PathVariable @Valid Long id){

		martialStatusDataService.deleteMartialStatusById(id);
				return ResponseHandler.generateSuccessResponse(
						 "martialStatus Successfully deleted", HttpStatus.OK);

	}

	
}

