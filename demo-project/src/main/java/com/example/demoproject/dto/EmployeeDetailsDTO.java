package com.example.demoproject.dto;

public class EmployeeDetailsDTO {
	
	private String employeeCode;
	
	private String firstName;
	
	
	public EmployeeDetailsDTO(String employeeCode, String firstName) {
		super();
		this.employeeCode = employeeCode;
		this.firstName = firstName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	@Override
	public String toString() {
		return "EmployeeDetailsDTO [employeeCode=" + employeeCode 
				+ ", firstName=" + firstName + "]";
	}
	
	

}

