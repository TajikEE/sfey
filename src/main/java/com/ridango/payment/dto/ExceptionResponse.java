package com.ridango.payment.dto;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
  public final HttpStatus status;
  public final String message;

  public ExceptionResponse(HttpStatus notFound, String message) {
    this.status = notFound;
    this.message = message;
  }

}