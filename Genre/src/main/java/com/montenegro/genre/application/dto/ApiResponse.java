package com.montenegro.genre.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

  private Boolean status;
  private String message;
  private LocalDateTime timestamp = LocalDateTime.now();

  // Constructor personalizado
  public ApiResponse(Boolean status, String message) {
    this.status = status;
    this.message = message;
    this.timestamp = LocalDateTime.now();
  }
}
