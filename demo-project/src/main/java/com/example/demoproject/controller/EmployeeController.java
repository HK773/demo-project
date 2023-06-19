package com.example.demoproject.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoproject.dto.EmployeeDetailsDTO;
import com.example.demoproject.dto.EmployeeLoginDetailsDTO;
import com.example.demoproject.entitymodel.Employee;
import com.example.demoproject.jwtsecurity.JWTService;
import com.example.demoproject.response.ResponseHandler;
import com.example.demoproject.service.EmployeeDataService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	private EmployeeDataService employeeDataService;
	
	 Logger logger = LoggerFactory.getLogger(getClass());
	
	public EmployeeController(EmployeeDataService employeeDataService) {
		this.employeeDataService = employeeDataService;
	}
	
	@Autowired
	private JWTService jwtService;
//	
//	
//	@Autowired
//	private AuthenticationManager authenticationManager;
	
//	ResponseEntity<Object>
	
	@PostMapping("/authenticate")
	public ResponseEntity<Object> authenticateAndGetToken(
		@RequestBody EmployeeLoginDetailsDTO employeeLoginDetails) 
	{
		
		System.out.println("Enter Authenticate ");
		
		if (employeeLoginDetails.getMailId().length() == 0) {
			System.out.println("Check for empty details ");
			throw new NoSuchElementException("Invalid User details");
		}
		
		System.out.println("Login details: " + employeeLoginDetails.getMailId() + " " 
				+ employeeLoginDetails.getPassword());
		
		if (employeeDataService.existsByMailId(employeeLoginDetails.getMailId())) {
			
			String data = jwtService.generateJWTToken(
					employeeLoginDetails.getMailId());
			
			return ResponseHandler.generateResponse(
					"Token generated Successfully", HttpStatus.OK, data);
			
		} else {
			return ResponseHandler.generateErrorResponse("Invalid Username", HttpStatus.BAD_REQUEST);
		}
		
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(
//						empVal.getMailId(), 
//						empVal.getPassword()));
//		
//		System.out.println("authentication" + employeeLoginDetails.getMailId());
//		
//		
//		if (authentication.isAuthenticated()) {
//			
//			System.out.println("Fun: authenticateAndGetToken");
//			
//			String data = jwtService.generateJWTToken(
//					employeeLoginDetails.getMailId());
//			
//			return ResponseHandler.generateResponse(
//					"Token generated Successfully", HttpStatus.OK, data);
//		}
//		 throw new UsernameNotFoundException("Invalid Username or Password..");
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/admin")
	public String adminPage() {
		return "Welcome to Admin page";
	}
		
//	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<@Valid Object> createEmployeeDetailsController(
			@RequestBody @Valid Employee reqEmployee)  {
		if (employeeDataService.existsByFirstName(
				reqEmployee.getFirstName())==true) {
			return ResponseHandler.generateErrorResponse(
					"Record Already exists", HttpStatus.CONFLICT);
		}
		
		try {
			Employee data = employeeDataService
					.saveEmployeeService(reqEmployee);
			
			if (data!=null) {
				
				sendMailController(data.getMailId());
				
				return ResponseHandler.generateResponse(
						"Successfully created Employee", 
						HttpStatus.OK, data);
			} else {
				
				return ResponseHandler.generateErrorResponse(
						"Check for either Mail ID or contact number", 
							HttpStatus.CONFLICT);		
			}
		} catch (DataIntegrityViolationException e) {
			
			return ResponseHandler.generateErrorResponse(
					((NestedRuntimeException) e).getMostSpecificCause().getMessage(), HttpStatus.CONFLICT);
		}
		
	}
	

//	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<Object> fetchEmployeeDetailsController(){
		
			List<Employee> employeeList = employeeDataService
					.retrieveAllEmployeeDetailsService();
			if (employeeList.isEmpty()) {
				return ResponseHandler.generateErrorResponse(
						"No Employee is added yet", HttpStatus.CONFLICT);
			} else {
				return ResponseHandler.generateResponse(
						"Employee list fetched successfully",
						HttpStatus.OK,
						employeeList);
			}
			
	}
	
	public void sendMailController(@Valid String mailId)  {
		if (mailId == null) {
			System.out.println("Invalid Mail Id");
		}
		employeeDataService.sendMail(mailId);
	}
	
	@PreAuthorize("hasAuthority('Employee')")
	@PutMapping("/user")
	public ResponseEntity<Object> updateEmployeePasswordController(
			@RequestBody @Valid EmployeeLoginDetailsDTO employeeLoginDetails){
		
		if (employeeLoginDetails.getMailId().length()==0) {
			throw new NoSuchElementException("email id not found");
		} else {
			
			employeeDataService.updateEmployeePasswordService(
					employeeLoginDetails.getMailId(), 
					employeeLoginDetails.getPassword());
			return ResponseHandler.generateSuccessResponse(
					"Successfully Re-set password", HttpStatus.OK); 
		}
		
		
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/{id}")
	public ResponseEntity<Object> fetchEmployeeDetailsByIdController(
			@PathVariable Long id){
		try {
			Employee data = employeeDataService.retrieveEmployeeDetailsByIdService(id);
			return ResponseHandler.generateResponse(
					"Successfully Fetched Employee", HttpStatus.OK, data);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateErrorResponse(
					"Failed to fetch employee", HttpStatus.CONFLICT);
		}
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateEmployeeDetailsController(
			@RequestBody Employee reqEmployee, @PathVariable Long id){
	
		
		if (employeeDataService.existsByFirstName(reqEmployee.getFirstName())==true &&
				employeeDataService.existsById(id)==true) {
			return ResponseHandler.generateErrorResponse(
					"Record Already exists", HttpStatus.CONFLICT);
			
		} else {
			
			try {
				
				Employee data = employeeDataService
						.updateEmployeeDetailsService(reqEmployee, id);
				if (data!=null) {
					return ResponseHandler.generateResponse(
							"Successfully updated Employee", HttpStatus.OK, data);
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
	@GetMapping("/list")
	public ResponseEntity<Object> retrieveEmployeeListController(){
		
		List<EmployeeDetailsDTO> empList = employeeDataService
				.fetchEmployeeListService();
		
		if (empList.isEmpty()) {	
			return ResponseHandler.generateErrorResponse(
					"No Employee is added yet", HttpStatus.BAD_REQUEST);
		}
		else {
			return ResponseHandler.generateResponse(
					"Successfully Fetched Employee", 
					HttpStatus.OK, 
					empList);
		}
		
	}
	
//	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteEmployeeController(
			@PathVariable Long id){

		if (employeeDataService.existsById(id)==false) {
			return ResponseHandler.generateErrorResponse(
					"Invalid ID", HttpStatus.CONFLICT); 
		} else {
			employeeDataService.deleteEmployeeDetailService(id);
			return ResponseHandler.generateSuccessResponse(
					"Employee record deleted successfully", HttpStatus.OK);
		}

	}
	
	
		
//	@GetMapping("/employee/admin-list")
//	public List<EmployeeDetailsDTO> getAllAdminList(){
//		
//		String roleName = "Admin";
//		List<EmployeeDetailsDTO> adminList = employeeDataService
//				.retrieveAdminListService(roleName);
//				
//		return adminList;
//			
//	}
	
//	@PutMapping("/{id}")
//	public ResponseEntity<Object> assignRoleController(
//			@RequestBody String roleName,
//			@PathVariable Long id){
//		
//		try {
//			Employee assignRole = employeeDataService
//					.assignRoleService(roleName, id);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
//	
//	
	
}

