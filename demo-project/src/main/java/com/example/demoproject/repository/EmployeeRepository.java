package com.example.demoproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoproject.dto.EmployeeDetailsDTO;
import com.example.demoproject.entitymodel.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//	/*select p from Product p left join fetch p.extras where p.id = :productId;*/
//	@Query("select  NEW com.dbsamples.basichrms.dto.EmployeeDetailsDTO(e.employeeCode, e.firstName) from Employee e"
//			+ "LEFT JOIN  FETCH e.role"
//			+ "WHERE r.roleName=:roleName")
//	List<EmployeeDetailsDTO> retrieveAdminList(@Param("roleName") String roleName);
	
	
	@Query("select NEW com.example.demoproject.dto.EmployeeDetailsDTO("
			+ "e.employeeCode, e.firstName) from Employee e ")
	List<EmployeeDetailsDTO> retriveEmployeeList();
	

	boolean existsByMailId(String mailId);
	
	boolean existsById(Long id);
	
	boolean existsByFirstName(String firstName);
	
	@Query("select e from Employee e where e.status=:reqStatus")
	List<Employee> findByStatus(@Param("reqStatus") String reqStatus);
	
	@Query("select e from Employee e where e.mailId=:reqMailId")
	Optional<Employee> findByMailId(@Param("reqMailId") String reqMailId);
	
	
	
}
