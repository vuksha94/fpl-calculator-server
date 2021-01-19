package com.springboot.fplcalculatorserver.exceptions;

public abstract class MyBusinessException extends RuntimeException {
  private static final long serialVersionUID = 7718828512143293558L;

  private final ErrorCode code;

  public MyBusinessException(ErrorCode code) {
    super();
    this.code = code;
  }

  public MyBusinessException(String message, Throwable cause, ErrorCode code) {
    super(message, cause);
    this.code = code;
  }

  public MyBusinessException(String message, ErrorCode code) {
    super(message);
    this.code = code;
  }

  public MyBusinessException(Throwable cause, ErrorCode code) {
    super(cause);
    this.code = code;
  }

  public ErrorCode getCode() {
    return this.code;
  }
}
