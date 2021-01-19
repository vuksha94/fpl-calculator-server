package com.springboot.fplcalculatorserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.springboot.fplcalculatorserver.models.ApiError;
import com.springboot.fplcalculatorserver.models.ApiResponse;

@ControllerAdvice()
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(MyAuthenticationException.class)
  protected ResponseEntity<ApiResponse<ApiError>> handleMyAuthenticationException(
      MyAuthenticationException ex) {
    final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
    return buildApiErrorResponse(apiError);
  }
  
  @ExceptionHandler(UsernameNotFoundException.class)
  protected ResponseEntity<ApiResponse<ApiError>> handleAuthenticationException(
      AuthenticationException ex) {
    String errorMsg = "Invalid username or password!";
    final ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, errorMsg);
    return buildApiErrorResponse(apiError);
    // return ResponseEntity.ok(new ApiResponse<String>().error(errorMsg));
  }
  
  @ExceptionHandler(MyEntityNotFoundException.class)
  protected ResponseEntity<ApiResponse<ApiError>> handleMyEntityNotFoundException(
      MyEntityNotFoundException ex) {
    final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
    return buildApiErrorResponse(apiError);
    // return ResponseEntity.ok(new ApiResponse<String>().error(errorMsg));
  }
  
  private ResponseEntity<ApiResponse<ApiError>> buildApiErrorResponse(ApiError apiError) {
    HttpStatus statusCode = apiError.getStatus();
    return ResponseEntity.status(statusCode).body(new ApiResponse<ApiError>().error(apiError));
  }
}
