package com.example.demoproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoproject.entitymodel.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	boolean existsByDepartmentName(String departmentName);
	
	boolean existsById(Long id);
	
	Department findByDepartmentName(String departmentName);
	
	@Query("Select d from Department d where d.status=:reqStatus")
	List<Department> findByStatus(@Param("reqStatus") String reqStatus);
}
