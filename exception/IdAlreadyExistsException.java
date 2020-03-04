package com.capgemini.storesmanagementsystem.exception;

public class IdAlreadyExistsException extends RuntimeException{
	
	String message;
	
	public IdAlreadyExistsException() {
		
		this.message="Id Already Exists Please Choose Another Id";
		
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
