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

import com.example.demoproject.entitymodel.BankAccountType;
import com.example.demoproject.response.ResponseHandler;
import com.example.demoproject.service.BankAccountTypeDataService;

@RestController
@RequestMapping("/bankaccounttype")
public class BankAccountTypeController {
	
	private BankAccountTypeDataService bankAccountTypeDataService;
	
	public BankAccountTypeController(
			BankAccountTypeDataService bankAccountTypeDataService) {
		
		this.bankAccountTypeDataService = bankAccountTypeDataService;
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Object> saveBankAccountTypeController(
			@RequestBody BankAccountType reqBankAccountType){
		
		if (bankAccountTypeDataService.existsByAccountType(
				reqBankAccountType.getAccountType())==true) {
			return ResponseHandler.generateErrorResponse(
					"Record Already exists", HttpStatus.CONFLICT);
		}
		
		BankAccountType data = bankAccountTypeDataService
				.createBankAccountTypeService(reqBankAccountType);
		
		if (data!=null) {
			return ResponseHandler.generateResponse(
					"Successfully created BankAccountType", 
					HttpStatus.CREATED, data);
		} else {
			return ResponseHandler.generateErrorResponse(
					"Failed to create BankAccountType", 
					HttpStatus.CONFLICT);
		}
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<Object> retrieveAllBankAccountType(){
		
			List<BankAccountType> listBankAccountTypes = bankAccountTypeDataService
					.retrieveAllBankAccountTypeService();
			
			if (listBankAccountTypes.isEmpty()) {
				ResponseHandler.generateErrorResponse(
						"List is Empty", HttpStatus.BAD_REQUEST);
			}
			
			return ResponseHandler.generateResponse(
					"Success", HttpStatus.OK, listBankAccountTypes);
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/demo")
	public ResponseEntity<Object> fetchAllBankAccountType(){
		
		List<BankAccountType> listBankAccountTypes = bankAccountTypeDataService
				.fetchAllBankAccountTypeService();
		
		if (listBankAccountTypes.isEmpty()) {
			ResponseHandler.generateErrorResponse(
					"List is Empty", HttpStatus.BAD_REQUEST);
		}
		
		return ResponseHandler.generateResponse(
				"Success", HttpStatus.OK, listBankAccountTypes);
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateBankAccountType(
			@RequestBody BankAccountType reqBankAccountType,
			@PathVariable Long id){
		
		if (bankAccountTypeDataService.existsByAccountType(
				reqBankAccountType.getAccountType())== true &&
				bankAccountTypeDataService.existsById(id)==true) {
			
			return ResponseHandler.generateErrorResponse(
					"Record Already Exists ", HttpStatus.CONFLICT);
			
		} else {
			
			try {
				
				BankAccountType data = bankAccountTypeDataService
						.updateBankAccountTypeByIdService(reqBankAccountType,id);
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
	public ResponseEntity<Object> delete(@PathVariable Long id){
		

		boolean data = bankAccountTypeDataService
							.deleteBankAccountTypeById(id);
		
		if (data) {
			return ResponseHandler.generateSuccessResponse(
					"Successfully deleted Bank Account Type",HttpStatus.OK );
		} else {
			return ResponseHandler.generateErrorResponse(
					"Invalid ID ", HttpStatus.CONFLICT);
		}

	}
	



}

