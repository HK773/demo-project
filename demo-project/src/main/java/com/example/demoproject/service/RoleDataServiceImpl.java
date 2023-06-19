package com.example.demoproject.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demoproject.entitymodel.Role;
import com.example.demoproject.repository.RoleRepository;


@Service
public class RoleDataServiceImpl implements RoleDataService{
	
	@Autowired
	@Qualifier("roleRepository")
	private RoleRepository roleRepository;
	
	@Override
	public boolean existsByRoleName(String roleName) {
		
		return roleRepository.existsByRoleName(roleName);
	}

	@Override
	public boolean existsById(Long id) {
		
		return roleRepository.existsById(id);
	}
	
	@Override
	public Role createRoleService(Role reqRole) {
		if (Objects.nonNull(reqRole)) {
			Role role = roleRepository.save(reqRole);
			return role;
		}
		return null;
	}

	@Override
	public List<Role> retrieveAllRolesService() {
		
		if (roleRepository.findAll().isEmpty()) {
			return null;
		} 
		
		List<Role> roleList = roleRepository.findAll();
		return roleList;
		
	}

	@Override
	public Role updateRoleService(Role role, Long id) {
		
		if (roleRepository.existsByRoleName(role.getRoleName())==true &&
				roleRepository.existsById(id)==false) {
			return null;
		} else {

			Role roleValue = roleRepository.findById(id).get();
			
			roleValue.setRoleName(role.getRoleName());
			roleValue.setStatus(role.getStatus());
			
			roleRepository.save(roleValue);
			
			return roleValue;
		}
	}

	@Override
	public boolean deleteRoleService(Long id) {
		
		if (roleRepository.existsById(id)==false) {
			return false;
		} else {
			
			Role roleData = roleRepository.findById(id).get();
			
			roleData.setStatus("InActive");	
			roleRepository.save(roleData);
			
//			roleRepository.deleteById(id);
			
			return true;
			
		}
	}
		
		@Override
		public List<Role> fetchAllRolesService() {
			if (roleRepository.findByStatus("Active")!=null) {
				return roleRepository.findByStatus("Active");
			}
			return null;
		}

		@Override
		public Role fetchRoleById(Long id) {
			return	roleRepository.findById(id).get();
		}

		@Override
		public Role saveRole(Role roleValue) {
			return roleRepository.save(roleValue);
		}
	


}

	

