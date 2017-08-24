package fr.bank.account;

import java.math.BigDecimal;

public class MoneyBuilder {
  public static MoneyBuilder money = new MoneyBuilder();
  private BigDecimal amount;

  public MoneyBuilder of(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  public Money build() {
    return new Money(amount);
  }
}