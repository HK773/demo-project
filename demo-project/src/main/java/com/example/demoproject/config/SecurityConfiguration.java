package com.example.demoproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demoproject.filter.JWTAuthFilter;
import com.example.demoproject.service.EmployeeUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
	
	@Autowired
	private JWTAuthFilter jwtAuthFilter;
	
	
	@Bean
	public UserDetailsService userDetailsService() {
			
		System.out.println("Fun: userDetailsService ");
		
		return new EmployeeUserDetailsService();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	
		System.out.println("Fun: passwordEncoder ");
		
		return new BCryptPasswordEncoder();
	}
	

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			
		System.out.println("Fun: securityFilterChain ");
		
		return http.csrf().disable()
			.authorizeHttpRequests()
			.requestMatchers("/admin","/v3/api-docs/**","/swagger-ui/**",
					"/employee/authenticate"
					,"/bankaccounttype/**","/department/**","/designation/**",
					"/employee/**","/employeetype/**","/gender/**",
					"/list","/martial-status/**","/shift/**","/role/**").permitAll()
			.and()
			.authorizeHttpRequests()
			.requestMatchers("/employee/user","/home")
			.authenticated()
			.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authenticationProvider(authenticationProvider())
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
			.build();
		
	}
	 
	 @Bean
	    public AuthenticationProvider authenticationProvider(){
			 
		 System.out.println("Fun: authenticationProvider ");
		 
	        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailsService());
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        return authenticationProvider;
	    }
	 
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		 
		 System.out.println("Fun: authenticationManager ");
		 
		return authConfig.getAuthenticationManager();
		 
	 }
	
}

