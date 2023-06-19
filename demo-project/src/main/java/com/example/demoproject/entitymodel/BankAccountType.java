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
@Table(name="bank_account_type")
public class BankAccountType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="accountType value is Empty")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message="Invalid account type value")
	@Size(min = 2, message="Field accountType is required")
	@Column(unique = true)
	private String accountType;
	
	@NotNull(message="status value is Empty")
	@Pattern(regexp = "^[a-zA-Z\\s]+$")
	private String status;
	
	@OneToMany(mappedBy = "bankAccountType", cascade = CascadeType.MERGE,
			fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employee> employee;
	
	public BankAccountType() {
		
	}
	

	public BankAccountType(
			@NotNull(message = "accountType value is Empty") @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid account type value") @Size(min = 2, message = "Field accountType is required") String accountType,
			@NotNull(message = "status value is Empty") @Pattern(regexp = "^[a-zA-Z\\s]+$") String status) {
		super();
		this.accountType = accountType;
		this.status = status;
	}



	public BankAccountType(
			@NotNull(message = "accountType value is needed") @Pattern(regexp = "^[a-zA-Z\\s]+$") String accountType,
			@NotNull(message = "status value is needed") @Pattern(regexp = "^[a-zA-Z\\s]+$") String status,
			List<Employee> employee) {
		super();
		this.accountType = accountType;
		this.status = status;
		this.employee = employee;
	}

	public BankAccountType(Long id,
			@NotNull(message = "accountType value is needed") @Pattern(regexp = "^[a-zA-Z\\s]+$") String accountType,
			@NotNull(message = "status value is needed") @Pattern(regexp = "^[a-zA-Z\\s]+$") String status,
			List<Employee> employee) {
		super();
		this.id = id;
		this.accountType = accountType;
		this.status = status;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
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
		return "BankAccountType [id=" + id + ", accountType=" + accountType + ", status=" + status + ", employee="
				+ employee + "]";
	}

	
	
	

}

