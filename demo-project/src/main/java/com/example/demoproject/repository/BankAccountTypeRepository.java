package com.example.demoproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoproject.entitymodel.BankAccountType;


@Repository
public interface BankAccountTypeRepository extends JpaRepository<
				BankAccountType, Long>{
	
	boolean existsByAccountType(String accountType);
	
	boolean existsById(Long id);
	
	@Query("select ba from BankAccountType ba where ba.status=:reqStatus")
	List<BankAccountType> findByStatus(@Param("reqStatus") String reqStatus);
}

