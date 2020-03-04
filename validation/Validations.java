package com.capgemini.storesmanagementsystem.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {
	public boolean emailValidation(String email) {
		String regExp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	public boolean passwordValidtion(String password) {
		String regExp = "^[\\w]{4,30}$";
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
	public boolean nameValidation(String name) {
		String regExp = "^[a-zA-Z]{4,50}";
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}
	
	public boolean dateValidation(String date) {
		String regExp = "^[0-9]{4}[-/](1[0-2]|0[1-9])[-/](3[01]|[12][0-9]|0[1-9])$";
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}
}
