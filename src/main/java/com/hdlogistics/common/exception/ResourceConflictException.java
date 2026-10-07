package com.hdlogistics.common.exception;

import org.springframework.http.HttpStatus;

public class ResourceConflictException extends BaseException {
  public ResourceConflictException(String errorCode, String message) {
    super(message, errorCode, HttpStatus.CONFLICT.value());
  }
}
