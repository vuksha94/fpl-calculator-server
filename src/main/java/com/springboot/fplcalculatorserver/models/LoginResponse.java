package com.springboot.fplcalculatorserver.models;

import com.springboot.fplcalculatorserver.models.fpl.FplManagerDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
	private String jwtToken;
	private FplManagerDetails fplManagerDetails;
}
