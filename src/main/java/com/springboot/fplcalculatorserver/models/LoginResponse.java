package com.springboot.fplcalculatorserver.models;

import java.io.Serializable;

import com.springboot.fplcalculatorserver.models.fpl.FplManagerDetails;

public class LoginResponse implements Serializable{
	
	private String jwtToken;
	private FplManagerDetails fplManagerDetails;

	public LoginResponse(String jwtToken, FplManagerDetails fplManagerDetails) {
		this.jwtToken = jwtToken;
		this.fplManagerDetails = fplManagerDetails;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public FplManagerDetails getFplManagerDetails() {
		return fplManagerDetails;
	}

	public void setFplManagerDetails(FplManagerDetails fplManagerDetails) {
		this.fplManagerDetails = fplManagerDetails;
	}
	
}
