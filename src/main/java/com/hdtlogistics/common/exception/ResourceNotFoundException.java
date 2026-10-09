package com.hdtlogistics.common.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseException {
  public ResourceNotFoundException(String errorCode, String message) {
    super(message, errorCode, HttpStatus.NOT_FOUND.value());
  }
}
