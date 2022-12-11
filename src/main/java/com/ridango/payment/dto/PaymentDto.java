package com.ridango.payment.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class PaymentDto {

  @NotNull(message = "Sender cannot be empty")
  private Long senderAccountId;

  @NotNull(message = "Receiver cannot be empty")
  private Long receiverAccountId;

  @NotNull(message = "Amount cannot be empty")
  private BigDecimal amount;

  public void setSenderAccountId(Long senderAccountId) {
    this.senderAccountId = senderAccountId;
  }

  public Long getSenderAccountId() {
    return senderAccountId;
  }

  public void setReceiverAccountId(Long receiverAccountId) {
    this.receiverAccountId = receiverAccountId;
  }

  public Long getReceiverAccountId() {
    return receiverAccountId;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getAmount() {
    return amount;
  }
}
