package com.example.demoproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoproject.entitymodel.EmployeeType;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Long> {
	

boolean existsByEmployeeTypeName(String employeeTypeName);
	
	boolean existsById(Long employeeTypeId);
	
	@Query("Select et from EmployeeType et where et.status=:reqStatus")
	List<EmployeeType> findByStatus(@Param("reqStatus") String reqStatus);
}

