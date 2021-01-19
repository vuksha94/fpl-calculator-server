package com.springboot.fplcalculatorserver.models;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ApiError {

  private LocalDateTime timestamp;
  private HttpStatus status;
  private String message;
  private String debugMessage;
  
  private ApiError() {
    timestamp = LocalDateTime.now();
  }
  public ApiError(HttpStatus status, String message) {
    this();
    this.status = status;
    this.message = message;
  }

}
