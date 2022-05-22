package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1047760345135750376L;
	String resourceName;
	String fieldName;
	long fieldvalue;
	
	public ResourceNotException(String resourceName, String fieldName, long fieldvalue) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName,fieldvalue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldvalue = fieldvalue;
	}
	
	
}
