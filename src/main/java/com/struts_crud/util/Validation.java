package com.struts_crud.util;

public class Validation{
	public static final String emailPattern = "^[a-zA-Z0-9._%±]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$";
	public boolean isEmailValid(String email) {
		if(email.matches(emailPattern)) {
			return true;
		}
		return false;
		
	}
	 public boolean doPasswordsMatch(String newPassword, String confirmPassword) {
	        return newPassword.equals(confirmPassword);
	    }
}