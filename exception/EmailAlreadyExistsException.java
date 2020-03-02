package com.capgemini.storesmanagementsystem.exception;

public class EmailAlreadyExistsException extends RuntimeException{
	String message;
	public EmailAlreadyExistsException(){
		this.message="Email Already Exists Please choose another email....";
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
