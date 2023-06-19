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
@Table(name="gender")
public class Gender {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	^[a-zA-Z\\s]+$ 
	@Pattern(regexp = "(?:male|Male|female|Female|FEMALE|MALE|\\s)$",
			message="Invalid Gendername")
	@NotNull(message="Gender value is empty")
	@Size(min = 3, message="genderName is required")
	@Column(unique = true)
	private String genderName;
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$",message="Invalid status" )
	@NotNull(message="status value is required")
	private String status;
	

	@OneToMany(mappedBy = "gender", cascade = CascadeType.MERGE,
			fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employee> employee;
	
	public Gender() {
		
	}
	
	public Gender(
			@Pattern(regexp = "(?:male|Male|female|Female|FEMALE|MALE|\\s)$", message = "Invalid Gendername") @NotNull(message = "Gender value is empty") @Size(min = 3, message = "genderName is required") String genderName,
			@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid status") @NotNull(message = "status value is required") String status) {
		super();
		this.genderName = genderName;
		this.status = status;
	}



	public Gender(
			@Pattern(regexp = "(?:m|M|male|Male|f|F|female|Female|FEMALE|MALE|Not prefer to say)$") @NotNull(message = "Invalid Gender value") String genderName,
			@Pattern(regexp = "^[a-zA-Z\\s]+$") @NotNull(message = "status value is needed") String status,
			List<Employee> employee) {
		super();
		this.genderName = genderName;
		this.status = status;
		this.employee = employee;
	}

	public Gender(Long id,
			@Pattern(regexp = "(?:m|M|male|Male|f|F|female|Female|FEMALE|MALE|Not prefer to say)$") @NotNull(message = "Invalid Gender value") String genderName,
			@Pattern(regexp = "^[a-zA-Z\\s]+$") @NotNull(message = "status value is needed") String status,
			List<Employee> employee) {
		super();
		this.id = id;
		this.genderName = genderName;
		this.status = status;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
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
		return "Gender [id=" + id + ", genderName=" + genderName + ", status=" + status + ", employee=" + employee
				+ "]";
	}

	
	
	
	
	
	
}
