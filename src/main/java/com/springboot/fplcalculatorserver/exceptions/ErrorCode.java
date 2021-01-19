package com.springboot.fplcalculatorserver.exceptions;

public enum ErrorCode {
  INVALID_CREDENTIALS("Invalid username or password!"),
  INVALID_JWT_SIGNATURE("Signature of JWT can't be trusted"),
  EMAIL_TAKEN("Email is already taken!"),
  PASSWORD_CONFIRMATION_ERROR("Password confirmation error!"),
  MANAGER_ID_NOT_FOUND("Manager with given id doesn't exist!"),
  LEAGUE_STANDINGS_NOT_FOUND("League standings with given id doesn't exist!"),
  MANAGER_PICKS_NOT_FOUND("Manager picks for given id and gw don't exist!");

  private final String msg;

  private ErrorCode(String msg) {
    this.msg = msg;
  }

  public String getMsg() {
    return msg;
  }

}
