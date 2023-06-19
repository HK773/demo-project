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
@Table(name="department")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private Long id; 
	
	@NotNull(message="departmentName value is Empty")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message="Invalid departmentname")
	@Size(min = 2, message="Field departmentName is required")
	@Column(unique = true)
	private String departmentName;
	
	@NotNull(message="status value is Empty")
	@Size(min = 3, message="status is required")
	@Pattern(regexp = "^[a-zA-Z\\s]+$",message="Invalid status" )
	private String status;
	
	@OneToMany(mappedBy = "department", cascade = CascadeType.MERGE,
			fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employee> employee;

	public Department() {
		
	}

	public Department(
			@NotNull(message = "departmentName value is needed") @Pattern(regexp = "^[a-zA-Z\\s]+$") String departmentName,
			@NotNull(message = "status value is needed") @Pattern(regexp = "^[a-zA-Z\\s]+$") String status,
			List<Employee> employee) {
		super();
		this.departmentName = departmentName;
		this.status = status;
		this.employee = employee;
	}
	
	public Department(Long id,
			@NotNull(message = "departmentName value is needed") @Pattern(regexp = "^[a-zA-Z\\s]+$") String departmentName,
			@NotNull(message = "status value is needed") @Pattern(regexp = "^[a-zA-Z\\s]+$") String status,
			List<Employee> employee) {
		super();
		this.id = id;
		this.departmentName = departmentName;
		this.status = status;
		this.employee = employee;
	}


	public Department(String departmentName, String status) {
		
		this.departmentName = departmentName;
		this.status=status;
		
		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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
		return "Department [id=" + id + ", departmentName=" + departmentName + ", status=" + status + ", employee="
				+ employee + "]";
	}
	
	
	

	
}


