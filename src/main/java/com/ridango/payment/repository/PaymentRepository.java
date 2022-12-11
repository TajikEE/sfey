package com.ridango.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ridango.payment.model.Payment;

@Component
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
