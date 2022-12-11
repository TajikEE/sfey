package com.ridango.payment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ridango.payment.dto.ExceptionResponse;
import com.ridango.payment.dto.PaymentDto;
import com.ridango.payment.exception.InsufficientBalanceException;
import com.ridango.payment.exception.ResourceNotFoundException;
import com.ridango.payment.model.Payment;
import com.ridango.payment.service.PaymentService;

@RestController
public class PaymentController {

  @Autowired
  private PaymentService paymentService;

  @PostMapping(value = "/payment", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Payment> createPayment(@RequestBody @Valid PaymentDto paymentDto) {
    return ResponseEntity.ok(paymentService.createPayment(paymentDto));

  }

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({ ResourceNotFoundException.class })
  public ExceptionResponse handleException(ResourceNotFoundException error) {
    return new ExceptionResponse(HttpStatus.NOT_FOUND, error.getMessage());
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({ InsufficientBalanceException.class })
  public ExceptionResponse handleException(InsufficientBalanceException error) {
    return new ExceptionResponse(HttpStatus.BAD_REQUEST, error.getMessage());
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({ MethodArgumentNotValidException.class })
  public ExceptionResponse handleException(MethodArgumentNotValidException error) {
    return new ExceptionResponse(HttpStatus.BAD_REQUEST,
        error.getBindingResult().getAllErrors().get(0).getDefaultMessage());
  }
}
