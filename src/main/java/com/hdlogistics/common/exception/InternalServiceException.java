package com.hdlogistics.common.exception;

import org.springframework.http.HttpStatus;

public class InternalServiceException extends BaseException {
  public InternalServiceException(String errorCode, String message) {
    super(message, errorCode, HttpStatus.INTERNAL_SERVER_ERROR.value());
  }
}
