package fr.bank.account;

import java.math.BigDecimal;

public class MoneyBuilder {
  public static MoneyBuilder money = new MoneyBuilder();
  private BigDecimal amount;

  public Money of(BigDecimal amount) {
    this.amount = amount;
    return new Money(amount);
  }
}