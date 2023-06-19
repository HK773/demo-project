package com.example.demoproject.service;

import java.util.List;

import com.example.demoproject.entitymodel.Role;

public interface RoleDataService {
	
boolean existsByRoleName(String roleName);
	
	boolean existsById(Long id);
	
	Role createRoleService(Role reqRole);
	
	List<Role> retrieveAllRolesService();
	
	List<Role> fetchAllRolesService();
	
	Role updateRoleService(Role role, Long id);
	
	boolean deleteRoleService(Long id);
	
	Role fetchRoleById(Long id);

	Role saveRole(Role roleValue);
}

