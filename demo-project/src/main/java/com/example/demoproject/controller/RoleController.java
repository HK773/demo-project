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

import com.example.demoproject.entitymodel.Role;
import com.example.demoproject.response.ResponseHandler;
import com.example.demoproject.service.RoleDataService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	private RoleDataService roleDataService;
	
	public RoleController(RoleDataService roleDataService) {
		this.roleDataService = roleDataService;
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Object> createRoleController(
			@RequestBody @Valid Role reqRole){
		
		if (roleDataService.existsByRoleName(
				reqRole.getRoleName())==true) {
			return ResponseHandler.generateErrorResponse(
					"Record Already exists", HttpStatus.CONFLICT);
		} 
		
		Role data = roleDataService.createRoleService(reqRole);
		
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
	public ResponseEntity<Object> retrieveAllRoleDetailsController(){
		List<Role> roleList = roleDataService.retrieveAllRolesService();
		if (roleList.isEmpty()) {
			return ResponseHandler.generateErrorResponse(
					"List is Empty", HttpStatus.BAD_REQUEST);
		}else {
			return ResponseHandler.generateResponse(
					"Successfully updated Department",
					HttpStatus.OK,
					roleList);
		}
		 
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/demo")
	public ResponseEntity<Object> fetchAllRoleDetailsController(){
		List<Role> roleList = roleDataService.fetchAllRolesService();
		if (roleList.isEmpty()) {
			return ResponseHandler.generateErrorResponse(
					"List is Empty", HttpStatus.BAD_REQUEST);
		}else {
			return ResponseHandler.generateResponse(
					"Successfully updated Department",
					HttpStatus.OK,
					roleList);
		}
		 
	}
		
//	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateRoleController(
			@RequestBody @Valid Role roleReq,
			@PathVariable @Valid Long id){
		
		if (roleDataService.existsByRoleName(roleReq.getRoleName())==true &&
				roleDataService.existsById(id)==true) {
			return ResponseHandler.generateErrorResponse(
					"Record Already Exists ", HttpStatus.CONFLICT);
		} else {
			
			try {
				Role data = roleDataService.updateRoleService(roleReq, id);
				if (data!=null) {
					return ResponseHandler.generateResponse(
							"Successfully updated Role", HttpStatus.OK, data);
				}else {
					return ResponseHandler.generateErrorResponse(
							"Invalid ID", HttpStatus.CONFLICT);
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
	public ResponseEntity<Object> deleteRoleByIdController(
			@PathVariable @Valid Long id) {

		boolean data = roleDataService.deleteRoleService(id);
		if (data) {
			return ResponseHandler.generateSuccessResponse(
					 "Role Successfully deleted", HttpStatus.OK);
		} else {
			return ResponseHandler.generateSuccessResponse(
					 "Invalid ID", HttpStatus.CONFLICT);
		}

	}
	
	
}

