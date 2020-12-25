package com.springboot.fplcalculatorserver.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fplcalculatorserver.entities.User;
import com.springboot.fplcalculatorserver.models.ApiResponse;
import com.springboot.fplcalculatorserver.models.LoginRequest;
import com.springboot.fplcalculatorserver.models.LoginResponse;
import com.springboot.fplcalculatorserver.models.RegisterRequest;
import com.springboot.fplcalculatorserver.models.fpl.FplManagerDetails;
import com.springboot.fplcalculatorserver.security.jwt.JwtUtil;
import com.springboot.fplcalculatorserver.services.FplApiService;
import com.springboot.fplcalculatorserver.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {
	
	@Autowired
	AuthenticationManager authenticationManager;
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private FplApiService fplApiService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest loginRequest) {
		final String email = loginRequest.getEmail();
		final String password = loginRequest.getPassword();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (AuthenticationException e) {
			return ResponseEntity.ok(new ApiResponse<String>().error("Invalid username or password!"));
		}
		
		final Optional<User> user = userService.findUserByEmail(email);
		final FplManagerDetails fplManagerDetails = 
				fplApiService.getManagersDetails(user.get().getFplManagerId()); // get details for manager from fpl api (no db storage yet)
		final String jwtToken = jwtUtil.generateToken(user.get());
		return ResponseEntity.ok(new ApiResponse<LoginResponse>().success(new LoginResponse(jwtToken, fplManagerDetails)));
	}
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<?>> register(@RequestBody RegisterRequest registerRequest) {
		//System.out.println(new ApiResponse().success(null));
		User registeredUser = null;
		try {
			registeredUser = userService.registerUser(registerRequest);
		} catch(Exception e) {
			return ResponseEntity.ok(new ApiResponse<String>().error(e.getMessage()));
		}
		return ResponseEntity.ok(new ApiResponse<User>().success(registeredUser));
	}
}
