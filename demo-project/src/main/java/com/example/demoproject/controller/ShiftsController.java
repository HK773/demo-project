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

import com.example.demoproject.entitymodel.Shifts;
import com.example.demoproject.response.ResponseHandler;
import com.example.demoproject.service.ShiftsDataService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/shift")
public class ShiftsController {
	
	private ShiftsDataService shiftsDataService;
	
	public ShiftsController(ShiftsDataService shiftsDataService) {
		this.shiftsDataService =shiftsDataService;
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Object> createShiftsController(
			@RequestBody @Valid Shifts reqShift){
		
		if (shiftsDataService.existsByShiftName(reqShift.getShiftName())) {
			return ResponseHandler.generateErrorResponse(
					"Record Already exists", HttpStatus.CONFLICT);
		} 
		
		Shifts data = shiftsDataService.createShiftsService(reqShift);
		
		if (data!=null) {
			
			return ResponseHandler.generateResponse(
					"Success", HttpStatus.OK, data);
		} else {
			
			return ResponseHandler.generateErrorResponse(
					"Failed to create Shifts", 
					HttpStatus.CONFLICT);
		}
		
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/demo")
	public ResponseEntity<Object> fetchAllShiftsController(){
		
		List<Shifts> shiftList = shiftsDataService
				.fetchAllShiftsService();
		if (shiftList.isEmpty()) {
			return ResponseHandler.generateErrorResponse(
					"shiftList is Empty", HttpStatus.BAD_REQUEST);
		}else {
			return ResponseHandler.generateResponse(
					"Successfully got Shifts",
					HttpStatus.OK,
					shiftList);
		}
	 
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<Object> retrieveAllShiftsController(){
		
		List<Shifts> shiftList = shiftsDataService
				.retrieveAllShiftsService();
		if (shiftList.isEmpty()) {
			return ResponseHandler.generateErrorResponse(
					"shiftList is Empty", HttpStatus.BAD_REQUEST);
		}else {
			return ResponseHandler.generateResponse(
					"Successfully got Shifts",
					HttpStatus.OK,
					shiftList);
		}	 
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateShiftsController(
			@RequestBody @Valid Shifts reqShift,
			@PathVariable @Valid Long id){
		
		if (shiftsDataService.existsByShiftName(
				reqShift.getShiftName())==true &&
				shiftsDataService.existsById(id)==true) {
			
			return ResponseHandler.generateErrorResponse(
					"Record Already exists", HttpStatus.CONFLICT);
			
		} else {
			
			try {
				
				Shifts data = shiftsDataService
						.updateShiftsByIdService(reqShift,id);
				
				if (data!= null) {
					return ResponseHandler.generateResponse(
							"Successfully updated Shifts", 
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
	public ResponseEntity<Object> deleteShiftsController(
			@PathVariable @Valid Long id){
		
		boolean data = shiftsDataService.deleteById(id);
		if (data) {
			 return ResponseHandler.generateSuccessResponse(
					 "Shift Successfully deleted", HttpStatus.OK);
		} else {
			return ResponseHandler.generateErrorResponse(
					"Invalid ID ", HttpStatus.CONFLICT);
		}
	}
	

}

