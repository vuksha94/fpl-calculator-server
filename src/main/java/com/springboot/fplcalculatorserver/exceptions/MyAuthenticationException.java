package com.springboot.fplcalculatorserver.exceptions;

public class MyAuthenticationException extends MyBusinessException {

  private static final long serialVersionUID = -7093649810851429556L;

  public MyAuthenticationException(String msg, Throwable cause, ErrorCode errorCode) {
    super(msg, cause, errorCode);
  }

  public MyAuthenticationException(String msg, ErrorCode errorCode) {
    super(msg, errorCode);
  }
}
