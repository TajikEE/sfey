package com.ridango.payment.service;

import org.springframework.stereotype.Component;

import com.ridango.payment.dto.PaymentDto;
import com.ridango.payment.model.Payment;

@Component
public interface PaymentService {
  Payment createPayment(PaymentDto paymentDto);
}
