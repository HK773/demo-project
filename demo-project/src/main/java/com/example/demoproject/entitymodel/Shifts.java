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
@Table(name="shifts")
public class Shifts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message="Invalid shiftName")
	@NotNull(message="shiftName value is empty")
	@Size(min = 3, message="shiftName is required")
	@Column(unique = true)
	private String shiftName;
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$")
	@NotNull(message="status value is needed")
	private String status;
	
	@OneToMany(mappedBy = "shifts", cascade = CascadeType.MERGE,
			fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employee> employee;
	
	public Shifts() {
		
	}

	public Shifts(@Pattern(regexp = "^[a-zA-Z\\s]+$") @NotNull(message = "Invalid shiftName value") String shiftName,
			@Pattern(regexp = "^[a-zA-Z\\s]+$") @NotNull(message = "status value is needed") String status,
			List<Employee> employee) {
		super();
		this.shiftName = shiftName;
		this.status = status;
		this.employee = employee;
	}

	public Shifts(Long id,
			@Pattern(regexp = "^[a-zA-Z\\s]+$") @NotNull(message = "Invalid shiftName value") String shiftName,
			@Pattern(regexp = "^[a-zA-Z\\s]+$") @NotNull(message = "status value is needed") String status,
			List<Employee> employee) {
		super();
		this.id = id;
		this.shiftName = shiftName;
		this.status = status;
		this.employee = employee;
	}

	public Shifts(String shiftName, String status) {
		this.shiftName = shiftName;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
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
		return "Shifts [id=" + id + ", shiftName=" + shiftName + ", status=" + status + ", employee=" + employee + "]";
	}
	
	
}

