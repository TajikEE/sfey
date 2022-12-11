package com.ridango.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ridango.payment.dto.PaymentDto;
import com.ridango.payment.exception.ResourceNotFoundException;
import com.ridango.payment.model.Account;
import com.ridango.payment.model.Payment;
import com.ridango.payment.repository.AccountRepository;
import com.ridango.payment.repository.PaymentRepository;

@Component
public class PaymentServiceImpl implements PaymentService {

  @Autowired
  PaymentRepository paymentRepository;

  @Autowired
  AccountRepository accountRepository;

  @Override
  public Payment createPayment(PaymentDto paymentDto) {

    Account senderAccount = accountRepository.findById(paymentDto.getSenderAccountId())
        .orElseThrow(() -> new ResourceNotFoundException("Sender account not found"));
    Account receiverAccount = accountRepository.findById(paymentDto.getReceiverAccountId())
        .orElseThrow(() -> new ResourceNotFoundException("Reciever account not found"));

    if (senderAccount.getBalance().compareTo(paymentDto.getAmount()) < 0) {
      throw new ResourceNotFoundException("Insufficient balance");
    }

    senderAccount.setBalance(senderAccount.getBalance().subtract(paymentDto.getAmount()));
    receiverAccount.setBalance(receiverAccount.getBalance().add(paymentDto.getAmount()));

    accountRepository.save(senderAccount);
    accountRepository.save(receiverAccount);

    Payment payment = new Payment();
    payment.setSenderAccount(senderAccount);
    payment.setReceiverAccount(receiverAccount);

    return paymentRepository.save(payment);

  }

}
