package com.capgemini.storesmanagementsystem.exception;

import java.util.InputMismatchException;

public class EnterValidInputException extends RuntimeException{
	
	String message;
	public EnterValidInputException() {
		this.message=" Enter Valid Input - Please Check Input and enter again.... ";
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
