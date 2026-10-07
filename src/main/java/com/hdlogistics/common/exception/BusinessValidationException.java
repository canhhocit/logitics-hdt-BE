package com.hdlogistics.common.exception;

import org.springframework.http.HttpStatus;

public class BusinessValidationException extends BaseException {
  public BusinessValidationException(String errorCode, String message) {
    super(message, errorCode, HttpStatus.BAD_REQUEST.value());
  }
}
