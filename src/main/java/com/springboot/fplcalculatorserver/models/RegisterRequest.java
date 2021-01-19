package com.springboot.fplcalculatorserver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	private long fplManagerId;
	private String email;
	private String password;
	private String confirmPassword;
}
