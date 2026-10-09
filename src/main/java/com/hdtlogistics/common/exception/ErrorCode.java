package com.hdtlogistics.common.exception;

public final class ErrorCode {
  public static final String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";
  public static final String TOKEN_EXPIRED = "TOKEN_EXPIRED";
  public static final String TOKEN_INVALID = "TOKEN_INVALID";
  public static final String TOKEN_REVOKED = "TOKEN_REVOKED";
  public static final String ACCESS_DENIED = "ACCESS_DENIED";
  public static final String ROLE_CODE_EXISTS = "ROLE_CODE_EXISTS";
  public static final String PERMISSION_CODE_NOT_FOUND = "PERMISSION_CODE_NOT_FOUND";
  public static final String PERMISSION_OVERRIDE_NOT_FOUND = "PERMISSION_OVERRIDE_NOT_FOUND";
  public static final String BRANCH_CODE_EXISTS = "BRANCH_CODE_EXISTS";
  public static final String DEPARTMENT_NOT_FOUND = "DEPARTMENT_NOT_FOUND";
  public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
  public static final String USERNAME_EXISTS = "USERNAME_EXISTS";
  public static final String EMAIL_EXISTS = "EMAIL_EXISTS";
  public static final String TIMESHEET_ALREADY_EXISTS = "TIMESHEET_ALREADY_EXISTS";
  public static final String GPS_OUT_OF_RANGE = "GPS_OUT_OF_RANGE";
  public static final String TIMESHEET_NOT_OWNER = "TIMESHEET_NOT_OWNER";
  public static final String REQUEST_TEMPLATE_NOT_FOUND = "REQUEST_TEMPLATE_NOT_FOUND";
  public static final String REQUEST_NOT_FOUND = "REQUEST_NOT_FOUND";
  public static final String REQUEST_NOT_PENDING = "REQUEST_NOT_PENDING";
  public static final String INSUFFICIENT_LEAVE_BALANCE = "INSUFFICIENT_LEAVE_BALANCE";
  public static final String CUSTOMER_CODE_EXISTS = "CUSTOMER_CODE_EXISTS";
  public static final String CUSTOMER_NOT_FOUND = "CUSTOMER_NOT_FOUND";
  public static final String QUOTE_NOT_FOUND = "QUOTE_NOT_FOUND";
  public static final String QUOTE_NOT_APPROVED = "QUOTE_NOT_APPROVED";
  public static final String QUOTE_EXPIRED = "QUOTE_EXPIRED";
  public static final String CONTRACT_NUMBER_EXISTS = "CONTRACT_NUMBER_EXISTS";
  public static final String CONTRACT_NOT_FOUND = "CONTRACT_NOT_FOUND";
  public static final String PROPOSAL_NOT_FOUND = "PROPOSAL_NOT_FOUND";
  public static final String PROPOSAL_NOT_PENDING = "PROPOSAL_NOT_PENDING";
  public static final String VALIDATION_ERROR = "VALIDATION_ERROR";
  public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
  public static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
  public static final String FORBIDDEN = "FORBIDDEN";
  public static final String UNAUTHORIZED = "UNAUTHORIZED";
  public static final String SLUG_EXISTS = "SLUG_EXISTS";
  public static final String INVALID_STATUS_TRANSITION = "INVALID_STATUS_TRANSITION";
  private ErrorCode() {}
}
