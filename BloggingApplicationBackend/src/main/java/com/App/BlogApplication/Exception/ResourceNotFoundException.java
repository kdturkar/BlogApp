package com.App.BlogApplication.Exception;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {
	String resourceName;
	String fieldName;
	int fieldValue;
	String fieldN;

	public ResourceNotFoundException(String resourceName, String fieldName, int fieldValue) {
		super(String.format("%s not found with %s : %d ", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public ResourceNotFoundException(String resourceName, String fieldName, String fieldN) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldN));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldN = fieldN;
	}
}
