package com.springboot.fplcalculatorserver.exceptions;

public class MyJwtException extends MyBusinessException {

  private static final long serialVersionUID = -6895697053878352526L;

  public MyJwtException(String msg, Throwable cause, ErrorCode errorCode) {
    super(msg, cause, errorCode);
  }

  public MyJwtException(String msg, ErrorCode errorCode) {
    super(msg, errorCode);
  }
}
