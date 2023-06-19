package com.example.demoproject.entitymodel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="employee_type")
public class EmployeeType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message="Invalid employeeTypeName")
	@NotNull(message="employeeTypeName value is empty")
	@Size(min = 2, message="employeeTypeName is required")
	@Column(unique = true)
	private String employeeTypeName;
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message="Invalid status")
	@NotNull(message="status value is empty")
	private String status;
	
	@OneToMany(mappedBy = "employeeTypes", cascade = CascadeType.MERGE,
			fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employee> employee;
	
	
	public EmployeeType() {
		
	}


	public EmployeeType(@Pattern(regexp = "^[a-zA-Z\\s]+$")
						@NotNull(message = "employeeTypeName value is needed") 
						String employeeTypeName,
						
						@Pattern(regexp = "^[a-zA-Z\\s]+$")
						@NotNull(message = "status value is needed")
						String status,
						List<Employee> employee) {
		super();
		this.employeeTypeName = employeeTypeName;
		this.status = status;
		this.employee = employee;
	}


	public EmployeeType(Long id, @NotNull(message = "employeeTypeName value is needed") String employeeTypeName,
			@Pattern(regexp = "^[a-zA-Z\\s]+$") @NotNull(message = "status value is needed") String status,
			List<Employee> employee) {
		super();
		this.id = id;
		this.employeeTypeName = employeeTypeName;
		this.status = status;
		this.employee = employee;
	}


	public EmployeeType(String employeeTypeName, String status) {
		super();
		this.employeeTypeName = employeeTypeName;
		this.status = status;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmployeeTypeName() {
		return employeeTypeName;
	}


	public void setEmployeeTypeName(String employeeTypeName) {
		this.employeeTypeName = employeeTypeName;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public List<Employee> getEmployee() {
		return employee;
	}


	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}


	@Override
	public String toString() {
		return "EmployeeType [id=" + id + ", employeeTypeName=" + employeeTypeName + ", status=" + status
				+ ", employee=" + employee + "]";
	}


	
	
	
}
