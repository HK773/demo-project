package com.example.demoproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoproject.entitymodel.Shifts;

@Repository
public interface ShiftsRepository extends JpaRepository<Shifts, Long> {
	
	boolean existsByShiftName(String shiftName);
	
	boolean existsById(Long id);
	
	@Query("Select s from Shifts s where s.status=:reqStatus")
	List<Shifts> findByStatus(@Param("reqStatus") String reqStatus);
}

