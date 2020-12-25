package com.springboot.fplcalculatorserver.models;

public class RegisterRequest {

	private long fplManagerId;
	private String email;
	private String password;
	private String confirmPassword;
	
	public long getManagerId() {
		return fplManagerId;
	}
	public void setManagerId(long managerId) {
		this.fplManagerId = managerId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}
