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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="martial_status")
public class MartialStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "(?:unmarried|UnMarried|Unmarried|married|Married|UNMARRIED|MARRIED|Not prefer to say\\s)$",
			message="Invalid martial status")
	@NotNull(message="martial status is empty")
	@Size(min = 3, message="martialStatusName is required")
	@Column(unique = true)
	private String martialStatusName;
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$",message="Invalid  status")
	@NotNull(message="status value is empty")
	@NotEmpty
	private String status;
	
	@OneToMany(mappedBy = "martialStatus", cascade = CascadeType.MERGE,
			fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employee> employee;
	
	public MartialStatus() {
		
	}

	public MartialStatus(
			@Pattern(regexp = "(?:UM|M|unmarried|UnMarried|Unmarried|married|Married|UNMARRIED|MARRIED|Not prefer to say)$") @NotNull(message = "Invalid martial status") String martialStatusName,
			@Pattern(regexp = "^[a-zA-Z\\s]+$") @NotNull(message = "status value is needed") @NotEmpty String status,
			List<Employee> employee) {
		super();
		this.martialStatusName = martialStatusName;
		this.status = status;
		this.employee = employee;
	}

	public MartialStatus(Long id,
			@Pattern(regexp = "(?:UM|M|unmarried|UnMarried|Unmarried|married|Married|UNMARRIED|MARRIED|Not prefer to say)$") @NotNull(message = "Invalid martial status") String martialStatusName,
			@Pattern(regexp = "^[a-zA-Z\\s]+$") @NotNull(message = "status value is needed") @NotEmpty String status,
			List<Employee> employee) {
		super();
		this.id = id;
		this.martialStatusName = martialStatusName;
		this.status = status;
		this.employee = employee;
	}

	public MartialStatus(String martialStatusName, String status) {
		super();
		this.martialStatusName = martialStatusName;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMartialStatusName() {
		return martialStatusName;
	}

	public void setMartialStatusName(String martialStatusName) {
		this.martialStatusName = martialStatusName;
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
		return "MartialStatus [id=" + id + ", martialStatusName=" + martialStatusName + ", status=" + status
				+ ", employee=" + employee + "]";
	}

	
	
}

