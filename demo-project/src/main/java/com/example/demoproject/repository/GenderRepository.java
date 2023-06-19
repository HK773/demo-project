package com.example.demoproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoproject.entitymodel.Gender;


@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {
	
boolean existsByGenderName(String genderName);
	
	boolean existsById(Long id);
	
	@Query("Select g from Gender g where g.status=:reqStatus")
	List<Gender> findByStatus(@Param("reqStatus") String reqStatus);
	
	

}

