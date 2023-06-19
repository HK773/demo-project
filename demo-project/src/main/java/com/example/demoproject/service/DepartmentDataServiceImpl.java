package com.example.demoproject.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demoproject.entitymodel.Department;
import com.example.demoproject.repository.DepartmentRepository;

@Service
public class DepartmentDataServiceImpl implements DepartmentDataService {
	
	@Autowired
	@Qualifier("departmentRepository")
	private DepartmentRepository departmentRepository;
	
	public DepartmentDataServiceImpl(DepartmentRepository departmentRepository2) {
		this.departmentRepository = departmentRepository2;
	}
	
	@Override
	public boolean existsByDepartmentName(String departmentName) {
		return departmentRepository.existsByDepartmentName(departmentName);
		
	}

	@Override
	public boolean existsById(Long id) {
		
		return departmentRepository.existsById(id);
	}

	
	@Override
	public Department saveDepartmentService(Department reqDepartment) {
		
		if (Objects.nonNull(reqDepartment)) {
			Department department = departmentRepository.save(reqDepartment);
			return department;
		}
		 
		return null;
	}

	@Override
	public List<Department> fetchAllDepartmentService() {
		
		if (departmentRepository.findByStatus("Active")!=null) {
			return departmentRepository.findByStatus("Active");
		}
		
		return null;
	}

	@Override
	public List<Department> retrieveAllDepartmentDetailsService() {
		
		if (departmentRepository.findAll().isEmpty()) {
			return null;
		}
		
		return departmentRepository.findAll();
		
	}

	@Override
	public Department updateDepartmentById(Department reqDepartment, Long id) {
		
		if (Objects.isNull(reqDepartment) || id==0) {
			
			return null;
		}

		if(	departmentRepository.existsByDepartmentName(
				reqDepartment.getDepartmentName())==true &&
				departmentRepository.existsById(id)==false) {
			return null;
		} 
		
		 else {
			
		Department departmentValue = departmentRepository.findById(id).get();
		
		departmentValue.setDepartmentName(reqDepartment.getDepartmentName());
		departmentValue.setStatus(reqDepartment.getStatus());

	
		departmentRepository.save(departmentValue);
	
		return departmentValue;
		}
		
	}

	@Override
	public boolean deleteDepartmentById(Long id) {
		
		if (departmentRepository.existsById(id)==false) {
			
			return false;
		} else {
			Department department = departmentRepository.findById(id).get();
			 department.setStatus("Inactive");
			 
			 departmentRepository.save(department);
			 departmentRepository.findByStatus(department.getStatus());
			 
//				departmentRepository.deleteById(id);
			 
		return true;
		}
		 

		
	}





}
