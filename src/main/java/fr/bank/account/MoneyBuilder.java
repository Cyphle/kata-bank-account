package fr.bank.account;

import java.math.BigDecimal;

public class MoneyBuilder {
  public static MoneyBuilder money = new MoneyBuilder();
  private BigDecimal amount;

  public MoneyBuilder withAmount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  public Money build() {
    return new Money(amount);
  }
}