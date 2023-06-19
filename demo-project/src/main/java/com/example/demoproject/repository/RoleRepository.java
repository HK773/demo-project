package com.example.demoproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoproject.entitymodel.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	
	Role findByroleName(String string);
	
	boolean existsByRoleName(String roleName);
	
	boolean existsById(Long id);
	
	@Query("select r from Role r where r.status=:reqStatus")
	List<Role> findByStatus(@Param("reqStatus") String reqStatus);
	

}
