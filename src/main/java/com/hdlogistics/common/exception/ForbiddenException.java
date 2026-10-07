package com.hdlogistics.common.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends BaseException {
  public ForbiddenException(String errorCode, String message) {
    super(message, errorCode, HttpStatus.FORBIDDEN.value());
  }
}
