package com.springboot.fplcalculatorserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.fplcalculatorserver.entities.User;
import com.springboot.fplcalculatorserver.models.ApiResponse;
import com.springboot.fplcalculatorserver.models.LoginRequest;
import com.springboot.fplcalculatorserver.models.LoginResponse;
import com.springboot.fplcalculatorserver.models.MyUserDetails;
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
  public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
    final String email = loginRequest.getEmail();
    final String password = loginRequest.getPassword();
    final Authentication authenticatedUser = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(email, password));
    System.out.println(authenticatedUser.getName());
    System.out.println(authenticatedUser.getPrincipal() instanceof MyUserDetails);
    final User user = ((MyUserDetails) (authenticatedUser.getPrincipal())).getUser();
    final FplManagerDetails fplManagerDetails =
        fplApiService.getManagersDetails(user.getFplManagerId()); // get details for manager from
                                                                  // fpl api (no db storage yet)
    final String jwtToken = jwtUtil.generateToken(user);
    
    return buildResponseEntity(new LoginResponse(jwtToken, fplManagerDetails));
  }

  @PostMapping("/register")
  public ResponseEntity<ApiResponse<User>> register(@RequestBody RegisterRequest registerRequest) {
    User registeredUser = null;
    registeredUser = userService.registerUser(registerRequest);
    return buildResponseEntity(registeredUser);
  }
  
  private <T> ResponseEntity<ApiResponse<T>> buildResponseEntity(T data) {
    return ResponseEntity.ok(new ApiResponse<T>().success(data));
  }
  
  private <T> void test(T data) {
    System.out.println("test T:" + data.getClass().getSimpleName());
  }
}
