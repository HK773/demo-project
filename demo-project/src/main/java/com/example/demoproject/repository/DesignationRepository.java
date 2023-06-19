package com.example.demoproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoproject.entitymodel.Designation;


@Repository
public interface DesignationRepository extends JpaRepository<Designation, Long>{
	

	boolean existsByDesignationName(String designationName);

	boolean existsById(long id);
	
	@Query("select ds from Designation ds where ds.status=:reqStatus")
	List<Designation> findByStatus(@Param("reqStatus")String reqStatus);
}

