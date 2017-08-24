package fr.bank.account;

import java.math.BigDecimal;
import java.math.MathContext;

public class MoneyBuilder {
  public static MoneyBuilder money = new MoneyBuilder();

  public Money of(BigDecimal amount) {
    return new Money(amount);
  }

  public Money of(double amount) {
    return new Money(new BigDecimal(amount, MathContext.DECIMAL64));
  }
}