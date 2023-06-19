package com.example.demoproject.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demoproject.entitymodel.Employee;

public class EmployeeInfoUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private String mailId;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public EmployeeInfoUserDetails(Employee employee) {
	
		mailId=employee.getMailId();
		password=employee.getPassword();
		authorities=employee.getRoles().stream()
							.map(role-> new SimpleGrantedAuthority(role.getRoleName()))
							.collect(Collectors.toList());
	}
	
	
	
	@Override
	public List<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		return mailId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


}

