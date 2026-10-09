package com.hdtlogistics.common.exception;

import com.hdtlogistics.common.dto.ErrorResponse;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(
      ResourceNotFoundException ex, WebRequest request) {
    return build(ex.getStatus(), ex.getErrorCode(), ex.getMessage(), request, null);
  }

  @ExceptionHandler(ResourceConflictException.class)
  public ResponseEntity<ErrorResponse> handleConflict(
      ResourceConflictException ex, WebRequest request) {
    return build(ex.getStatus(), ex.getErrorCode(), ex.getMessage(), request, null);
  }

  @ExceptionHandler(BusinessValidationException.class)
  public ResponseEntity<ErrorResponse> handleBusinessValidation(
      BusinessValidationException ex, WebRequest request) {
    return build(ex.getStatus(), ex.getErrorCode(), ex.getMessage(), request, null);
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<ErrorResponse> handleForbidden(ForbiddenException ex, WebRequest request) {
    return build(ex.getStatus(), ex.getErrorCode(), ex.getMessage(), request, null);
  }

  @ExceptionHandler(InternalServiceException.class)
  public ResponseEntity<ErrorResponse> handleInternalService(
      InternalServiceException ex, WebRequest request) {
    return build(ex.getStatus(), ex.getErrorCode(), ex.getMessage(), request, null);
  }

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<ErrorResponse> handleBase(BaseException ex, WebRequest request) {
    return build(ex.getStatus(), ex.getErrorCode(), ex.getMessage(), request, null);
  }

  @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
  public ResponseEntity<ErrorResponse> handleNoHandlerFound(
      org.springframework.web.servlet.NoHandlerFoundException ex, WebRequest request) {
    return build(
        HttpStatus.NOT_FOUND.value(),
        "NOT_FOUND",
        "API không tồn tại",
        request,
        null);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidation(
      MethodArgumentNotValidException ex, WebRequest request) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            error -> {
              String field = ((FieldError) error).getField();
              errors.put(field, error.getDefaultMessage());
            });
    return build(
        HttpStatus.BAD_REQUEST.value(),
        ErrorCode.VALIDATION_ERROR,
        "Dữ liệu đầu vào không hợp lệ",
        request,
        errors);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegal(
      IllegalArgumentException ex, WebRequest request) {
    return build(
        HttpStatus.BAD_REQUEST.value(), ErrorCode.VALIDATION_ERROR, ex.getMessage(), request, null);
  }

  @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
  public ResponseEntity<ErrorResponse> handleAccessDenied(
      org.springframework.security.access.AccessDeniedException ex, WebRequest request) {
    return build(
        HttpStatus.FORBIDDEN.value(),
        "FORBIDDEN",
        "Bạn không có quyền thực hiện thao tác này",
        request,
        null);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleAll(Exception ex, WebRequest request) {
    ex.printStackTrace();
    return build(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        ErrorCode.INTERNAL_SERVER_ERROR,
        "Lỗi hệ thống ngoài dự kiến",
        request,
        null);
  }

  private ResponseEntity<ErrorResponse> build(
      int status,
      String errorCode,
      String message,
      WebRequest request,
      Map<String, String> validationErrors) {
    ErrorResponse body =
        ErrorResponse.builder()
            .timestamp(Instant.now().toString())
            .status(status)
            .error(errorCode)
            .message(message)
            .path(getPath(request))
            .validationErrors(validationErrors)
            .build();
    return ResponseEntity.status(status).body(body);
  }

  private String getPath(WebRequest request) {
    if (request instanceof ServletWebRequest swr) {
      return swr.getRequest().getRequestURI();
    }
    return request.getDescription(false).replace("uri=", "");
  }
}
