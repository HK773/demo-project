package com.example.demoproject.entitymodel;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="role_details")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message="Invalid roleName")
	@NotNull(message="roleName value is empty")
	@Size(min = 2, message=" Role name is required")
	@Column(unique = true)
	private String roleName;
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$")
	@NotNull(message="status value is needed")
	private String status;

	
	@ManyToMany( cascade = CascadeType.MERGE)
	@JoinTable(name="roles_employee",
			  joinColumns= @JoinColumn(
					  name="role_details_id", referencedColumnName = "id"),
			  inverseJoinColumns= @JoinColumn(
					  name="employee_id", referencedColumnName = "id"))
	@JsonIgnore
	private List<Employee> employee= new ArrayList<>();
	
	
	public Role() {
		
	}

	public Role(@Pattern(regexp = "^[a-zA-Z\\s]+$") @NotNull(message = "roleName value is needed") String roleName,
			@Pattern(regexp = "^[a-zA-Z\\s]+$") @NotNull(message = "status value is needed") String status,
			List<Employee> employee) {
		super();
		this.roleName = roleName;
		this.status = status;
		this.employee = employee;
	}

	public Role(Long id,
			@Pattern(regexp = "^[a-zA-Z\\s]+$") @NotNull(message = "roleName value is needed") String roleName,
			@Pattern(regexp = "^[a-zA-Z\\s]+$") @NotNull(message = "status value is needed") String status,
			List<Employee> employee) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.status = status;
		this.employee = employee;
	}

	public Role(String roleName, String status) {
		super();
		this.roleName = roleName;
		this.status = status;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
		return "Role [id=" + id + ", roleName=" + roleName + ", status=" + status + ", employee=" + employee + "]";
	}
	
	
	
}

