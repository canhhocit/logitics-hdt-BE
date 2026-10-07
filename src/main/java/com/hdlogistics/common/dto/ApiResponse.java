package com.hdlogistics.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

  @Builder.Default private boolean success = true;

  private T data;

  private String message;

  public static <T> ApiResponse<T> success(T data) {
    return ApiResponse.<T>builder().success(true).data(data).message("Thành công").build();
  }

  public static <T> ApiResponse<T> success(T data, String message) {
    return ApiResponse.<T>builder().success(true).data(data).message(message).build();
  }

  public static ApiResponse<Void> noContent() {
    return ApiResponse.<Void>builder().success(true).message("Thành công").build();
  }
}
