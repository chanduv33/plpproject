package com.capgemini.storesmanagementsystem.exception;

import java.util.InputMismatchException;

public class EnterValidInputException extends RuntimeException{
	
	String message;
	public EnterValidInputException() {
		this.message=" Exception Occurred - Enter Valid Input \n Please Check Input and enter again.... ";
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
