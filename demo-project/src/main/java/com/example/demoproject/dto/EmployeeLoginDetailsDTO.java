package com.example.demoproject.dto;

public class EmployeeLoginDetailsDTO {
	
	private String mailId;
	
	private String password;
	
	public EmployeeLoginDetailsDTO() {
		
	}
	
	public EmployeeLoginDetailsDTO(String mailId, String password) {
		super();
		this.mailId = mailId;
		this.password = password;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "EmployeeLoginDetailsDTO [mailId =" + mailId + ", password=" + password + "]";
	}

	
	
}

