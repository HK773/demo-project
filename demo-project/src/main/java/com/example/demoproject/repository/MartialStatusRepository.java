package com.example.demoproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoproject.entitymodel.MartialStatus;


@Repository
public interface MartialStatusRepository extends JpaRepository<MartialStatus, Long>{
	
	boolean existsByMartialStatusName(String martialStatusName);
	
	boolean existsById(Long id);
	
	@Query("Select m from MartialStatus m where m.status=:reqStatus")
	List<MartialStatus> findByStatus(@Param("reqStatus") String reqStatus);
	
}

