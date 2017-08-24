package fr.bank.account;

import java.math.BigDecimal;

public class Money {
  private BigDecimal amount;

  Money(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getAmount() {
    return amount;
  }
}
