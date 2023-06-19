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
@Table(name="designation")
public class Designation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private Long id;
	
	@NotNull(message="designationName value is Empty")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message="designationName is invalid ")
	@Size(min = 2, message="Feild designationName is required")
	@Column(unique = true)
	private String designationName;
	
	@NotNull(message="status value is empty")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message="Status is Invalid")
	private String status;
	
	@OneToMany(mappedBy = "designation",cascade = CascadeType.MERGE,
			fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employee> employee;
	
	public Designation() {
		
	}
	
	public Designation(
			@NotNull(message = "designationName value is Empty") @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "designationName is invalid ") @Size(min = 2, message = "Feild designationName is required") String designationName,
			@NotNull(message = "status value is empty") @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Status is Invalid") String status) {
		super();
		this.designationName = designationName;
		this.status = status;
	}

	public Designation(
			@NotNull(message = "designationName value is needed") @Pattern(regexp = "^[a-zA-Z\\s]+$") String designationName,
			@NotNull(message = "status value is needed") @Pattern(regexp = "^[a-zA-Z\\s]+$") String status,
			List<Employee> employee) {
		super();
		this.designationName = designationName;
		this.status = status;
		this.employee = employee;
	}

	public Designation(Long id,
			@NotNull(message = "designationName value is needed") @Pattern(regexp = "^[a-zA-Z\\s]+$") String designationName,
			@NotNull(message = "status value is needed") @Pattern(regexp = "^[a-zA-Z\\s]+$") String status,
			List<Employee> employee) {
		super();
		this.id = id;
		this.designationName = designationName;
		this.status = status;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
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
		return "Designation [id=" + id + ", designationName=" + designationName + ", status=" + status + ", employee="
				+ employee + "]";
	}

	
	
	
	
}
