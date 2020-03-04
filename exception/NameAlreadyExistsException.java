package com.capgemini.storesmanagementsystem.exception;

public class NameAlreadyExistsException extends RuntimeException{
	String message;
	public NameAlreadyExistsException() {
		this.message="Name Already Existing Please choose another name ..";
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
