package com.example.demoproject.response;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	
	public static ResponseEntity<Object> generateResponse(String message, 
			HttpStatus status, Object responseObject) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("message", message);
		map.put("status", status);
		map.put("data", responseObject);
		return new ResponseEntity<Object>(map,status);
		
	}
	
	
	public static ResponseEntity<Object> generateErrorResponse(String message, 
			HttpStatus status) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("message", message);
		map.put("status", status);
		return new ResponseEntity<Object>(map,status);
		
	}
	
	public static ResponseEntity<Object> generateSuccessResponse(String message, 
			HttpStatus status) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("message", message);
		map.put("status", status);
		return new ResponseEntity<Object>(map,status);
		
	}


	
	
	
}

