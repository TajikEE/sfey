package com.ridango.payment;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.ridango.payment.dto.PaymentDto;
import com.ridango.payment.model.Account;
import com.ridango.payment.model.Payment;
import com.ridango.payment.repository.AccountRepository;
import com.ridango.payment.repository.PaymentRepository;
import com.ridango.payment.service.PaymentServiceImpl;

import java.util.Optional;

@SpringBootTest(classes = PaymentApplication.class)
@AutoConfigureMockMvc
public class PaymentEndToEndTest {

  @Mock
  PaymentRepository paymentRepository;

  @Mock
  AccountRepository accountRepository;

  @InjectMocks
  PaymentServiceImpl paymentService;

  @Test
  public void shouldUpdateSenderAccount() {
    Account senderAccount = new Account();
    senderAccount.setAccountId(1L);
    senderAccount.setBalance(new BigDecimal(100));

    Account receiverAccount = new Account();
    receiverAccount.setAccountId(2L);
    receiverAccount.setBalance(new BigDecimal(0));

    PaymentDto paymentDto = new PaymentDto();
    paymentDto.setSenderAccountId(1L);
    paymentDto.setReceiverAccountId(2L);
    paymentDto.setAmount(new BigDecimal(100));

    Payment expectedPayment = new Payment();
    expectedPayment.setReceiverAccount(receiverAccount);
    expectedPayment.setSenderAccount(senderAccount);

    when(accountRepository.findById(1L)).thenReturn(Optional.of(senderAccount));
    when(accountRepository.findById(2L)).thenReturn(Optional.of(receiverAccount));
    when(paymentRepository.save(any())).thenAnswer((input) -> input.getArgument(0));

    Payment result = paymentService.createPayment(paymentDto);

    assertEquals(result.getSenderAccount().getAccountId(), 1L);
    assertEquals(result.getReceiverAccount().getAccountId(), 2L);
    assertEquals(result.getSenderAccount().getBalance(), new BigDecimal(0));
    assertEquals(result.getReceiverAccount().getBalance(), new BigDecimal(100));
  }

}
