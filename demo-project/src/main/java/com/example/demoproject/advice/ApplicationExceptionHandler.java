package com.example.demoproject.advice;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleInvalidArgument(
			MethodArgumentNotValidException invalidException){
		
		logger.debug("Fun: handleInvalidArgument ");
		
		Map<String,String> errorMap = new HashMap<>();
		invalidException.getBindingResult()
						.getFieldErrors()
						.forEach(error->{
							errorMap.put(
									error.getField(), 
									error.getDefaultMessage());
						});
		 return errorMap;
	}
	
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Map<String,String> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException exception){
		
		logger.debug("Fun: handleHttpRequestMethodNotSupportedException ");
		
		Map<String,String> errorMap = new HashMap<>();
		
		errorMap.put(exception.getCause().getMessage(),
				exception.getMessage());
		
		 return errorMap;
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Map<String,String> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException exception){
		
		logger.debug("Fun: handleHttpMessageNotReadableException ");
		
		Map<String,String> errorMap = new HashMap<>();
		
		errorMap.put("Invalid Date format:",exception.getCause().getMessage());
		
		 return errorMap;
	}
	
	
	@ExceptionHandler(NullPointerException.class)
	public Map<String,String> handleNullPointerException(
			NullPointerException exception){
		
		Map<String,String> errorMap = new HashMap<>();
		
		errorMap.put("Null value found:",exception.getCause().getMessage());
		
		 return errorMap;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public Map<String,String> handleDataIntegrityViolationException(
			DataIntegrityViolationException exception){
		
		logger.debug("Fun: handleDataIntegrityViolationException ");
		
		Map<String,String> errorMap = new HashMap<>();
		
		errorMap.put("Duplicate Entry",exception.getCause().getMessage());
		
		 return errorMap;
	}
	

}

