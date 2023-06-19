package com.example.demoproject.entitymodel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = {
		   @UniqueConstraint(name = "UniqueBankAccountNumber",
				   columnNames = {"bankAccountNumber"}),
		   @UniqueConstraint(name = "UniqueEsiNumber",
		   columnNames = {"esiNumber"}),
		   @UniqueConstraint(name = "UniquePfNumber",
		   columnNames = {"pfNumber"}),
		   @UniqueConstraint(name = "UniqueUanNumber",
		   columnNames = {"uanNumber"}),
		   @UniqueConstraint(name = "UniquePanNumber",
		   columnNames = {"panNumber"}),
		   @UniqueConstraint(name = "UniqueAadharCardNumber",
		   columnNames = {"aadharCardNumber"}),
		   @UniqueConstraint(name = "UniqueEmployeeCode",
		   columnNames = {"employeeCode"})
		   })

public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="employeeCode value is Empty")
	@Pattern(regexp = "^DB\\d+|^DBI\\d+", message="Invalid employeeCode")
	@Size(min = 5,message="employeeCode length should be at least 5")
	private String employeeCode;
	
	@Pattern(regexp = "^[\\p{L} .'-]+$",message="Invalid firstName")
	@NotBlank(message="firstName value is Empty")
	@Size(min = 3,message="firstName length should be at least 3")
	private String firstName;
	
	@Size(min = 10,message="contactNumber length should be 10")
	@Pattern(regexp = "\\d+",message="Invalid contactNumber")
	@NotBlank(message="contactNumber value is empty")
	@Column(name="contact_number",unique = true)
	private String contactNumber;
	
	@Email(message="Invalid Email Id")
	@Pattern(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
	message="Invalid mail id")
	@Column(name="mail_id",unique = true)
	private String mailId;
	
	@Size(min = 9,message="password length should be 9")
//	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{9,15})", message="Invalid password")
	@JsonIgnore
	private String password;
	
	@ManyToMany(mappedBy = "employee", fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="employee_type_id")
	private EmployeeType employeeTypes;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;
	
	@ManyToOne
	@JoinColumn(name="designation_id")
	private Designation designation;
	
	@ManyToOne
	@JoinColumn(name="shifts_id")
	private Shifts shifts;
	
	@ManyToOne
	@JoinColumn(name="martial_status_id")
	private MartialStatus martialStatus;
	
	@ManyToOne
	@JoinColumn(name="gender_id")
	private Gender gender;
	
	@ManyToOne
	@JoinColumn(name="account_type_id")
	private BankAccountType bankAccountType;
	
	@Size(min = 12, message="bankAccountNumber should be 12 digits ")
	@Pattern(regexp = "\\d+", message="bankAccountNumber is required ")
	@NotBlank(message="bankAccountNumber value is empty")
	private String bankAccountNumber;
	
	@NotNull(message="universityName value is empty")
	@Pattern(regexp = "^[\\p{L} .'-]+$",message="Invalid universityName" )
	@Size(min = 3,message="universityName should be at least 3 characters")
	private String universityName;
	
	@NotNull(message="highestQualification value is empty")
	@Pattern(regexp = "^[\\p{L} .'-]+$", message="Invalid highestQualification")
	@Size(min = 3,message="highestQualification should be at least 3 characters")
	private String highestQualification;
	
	@DateTimeFormat(pattern = "yyyy")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy")
	private Date yearOfPassing;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private LocalDate dateOfBirth;
	
	@NotNull(message="fatherName value is Empty")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message="Invalid fatherName")
	@Size(min = 3,message="fatherName should be at least 3 characters")
	private String fatherName;
	
	@NotNull(message="motherName value is empty")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message="Invalid motherName")
	@Size(min = 3,message="motherName should be at least 3 characters")
	private String motherName;
	
	@NotNull(message="age value is empty")
	@Pattern(regexp = "\\d+",message="Invalid age")
	@Size(max = 2,message="age number id required ")
	@Min(value = 18,message="Minimum age value should be 18")
	@Max(value = 60, message="Maximum age value should be 60")
	private String age;
	
	@NotNull(message="bloodGroup value is empty")
	@Pattern(regexp = "^(A|B|AB|O)[+-]+$",message="Invalid bloodGroup")
	@Size(min = 2,message="bloodGroup should be at least 2 characters")
	private String bloodGroup;
	
	@Email(message="Invalid Email Id")
	@Pattern(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
	message="Invalid MAIL ID pattern")
	@Column(name="personal_mail_id",unique = true)
	private String personalMailId;
	
	
	@NotNull(message="esiNumber value is empty")
	@Pattern(regexp = "\\d+",message="Invalid esiNumber")
	@Size(min = 17,message="esiNumber should contain 17 digits ")
	private String esiNumber;
	
	@NotNull(message="pfNumber value is empty")
	@Pattern(regexp = "^[a-zA-Z0-9]{22}$",message="Invalid pfNumber")
	@Size(min = 22,message="pfNumber should contain 22 characters")
	private String pfNumber;
	
	@NotNull(message="uanNumber value is empty")
	@Pattern(regexp = "^\\d+",message="Invalid uanNumber")
	@Size(min = 12,message="uanNumber should contain 12 digits")
	private String uanNumber;
	
	@NotNull(message="panNumber value is empty")
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}",
	message="panNumber should contain 10 characters" )
	@Size(min = 10,message="Invalid panNumber")
	private String panNumber;
	
	@NotNull(message="aadharCardNumber value is empty")
	@Pattern(regexp = "\\d+",message="AadharCardNumber should contain 12 digits")
	@Size(min = 12,message="aadharCardNumber should contain 12 characters")
	private String aadharCardNumber;
	
	@Column(name="permanent_address")
	@NotNull(message="permanentAddress value is empty")
	@Size(min = 5,message="permanentAddress should be at least 3 characters")
	private String permanentAddress;
	
	@Column(name="postal_address")
	@NotNull(message="postalAddress value is empty")
	@Size(min =5 ,message="postalAddress should be at least 3 characters")
	private String postalAddress;
	
	@Pattern(regexp = "^\\d+",message="Invalid emergencyContactNumber")
	@NotNull(message="emergencyContactNumber value is empty")
	@Size(min = 10,message="emergencyContactNumber should 10 digits")
	@Column(name="emergency_contact_number",unique = true)
	private String emergencyContactNumber;
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$",message="Invalid emergencyContactName" )
	@NotNull(message="emergencyContactName value is empty")
	@Size(min = 3,
	message="emergencyContactName should be at least 3 characters")
	private String emergencyContactName;
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$",message="Invalid emergencyContactRelation")
	@NotNull(message="emergencyContactRelation value is empty")
	@Size(min = 5,
	message="emergencyContactRelation should be at least 5 characters")
	private String emergencyContactRelation;
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$",message="Invalid status")
	@NotNull(message="status value is empty")
	private String status;
	
	public Employee() {
		
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public EmployeeType getEmployeeTypes() {
		return employeeTypes;
	}

	public void setEmployeeTypes(EmployeeType employeeTypes) {
		this.employeeTypes = employeeTypes;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public Shifts getShifts() {
		return shifts;
	}

	public void setShifts(Shifts shifts) {
		this.shifts = shifts;
	}

	public MartialStatus getMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(MartialStatus martialStatus) {
		this.martialStatus = martialStatus;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public BankAccountType getBankAccountType() {
		return bankAccountType;
	}

	public void setBankAccountType(BankAccountType bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}

	public Date getYearOfPassing() {
		return yearOfPassing;
	}

	public void setYearOfPassing(Date yearOfPassing) {
		this.yearOfPassing = yearOfPassing;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getPersonalMailId() {
		return personalMailId;
	}

	public void setPersonalMailId(String personalMailId) {
		this.personalMailId = personalMailId;
	}

	public String getEsiNumber() {
		return esiNumber;
	}

	public void setEsiNumber(String esiNumber) {
		this.esiNumber = esiNumber;
	}

	public String getPfNumber() {
		return pfNumber;
	}

	public void setPfNumber(String pfNumber) {
		this.pfNumber = pfNumber;
	}

	public String getUanNumber() {
		return uanNumber;
	}

	public void setUanNumber(String uanNumber) {
		this.uanNumber = uanNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getAadharCardNumber() {
		return aadharCardNumber;
	}

	public void setAadharCardNumber(String aadharCardNumber) {
		this.aadharCardNumber = aadharCardNumber;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getEmergencyContactNumber() {
		return emergencyContactNumber;
	}

	public void setEmergencyContactNumber(String emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}

	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public String getEmergencyContactRelation() {
		return emergencyContactRelation;
	}

	public void setEmergencyContactRelation(String emergencyContactRelation) {
		this.emergencyContactRelation = emergencyContactRelation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeCode=" + employeeCode + ", firstName=" + firstName + ", contactNumber="
				+ contactNumber + ", mailId=" + mailId + ", password=" + password 
				+ ", roles=" + roles + ", employeeTypes=" + employeeTypes
				+ ", department=" + department + ", designation=" + designation + ", shifts=" + shifts
				+ ", martialStatus=" + martialStatus + ", gender=" + gender + ", bankAccountType=" + bankAccountType
				+ ", bankAccountNumber=" + bankAccountNumber + ", universityName=" + universityName
				+ ", highestQualification=" + highestQualification + ", yearOfPassing=" + yearOfPassing
				+ ", dateOfBirth=" + dateOfBirth + ", fatherName=" + fatherName + ", motherName=" + motherName
				+ ", age=" + age + ", bloodGroup=" + bloodGroup + ", personalMailId=" + personalMailId + ", esiNumber="
				+ esiNumber + ", pfNumber=" + pfNumber + ", uanNumber=" + uanNumber + ", panNumber=" + panNumber
				+ ", aadharCardNumber=" + aadharCardNumber + ", permanentAddress=" + permanentAddress
				+ ", postalAddress=" + postalAddress + ", emergencyContactNumber=" + emergencyContactNumber
				+ ", emergencyContactName=" + emergencyContactName + ", emergencyContactRelation="
				+ emergencyContactRelation + ", status=" + status + "]";
	}

	
	
}

