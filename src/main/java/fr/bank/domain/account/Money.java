package fr.bank.domain.account;

import java.math.BigDecimal;
import java.math.MathContext;

public class Money {
  public static final MoneyBuilder money = new MoneyBuilder();
  private final BigDecimal amount;

  private Money(BigDecimal amount) {
    this.amount = amount;
  }

  BigDecimal getAmount() {
    return amount;
  }

  Money plus(Money amount) {
    return money.of(this.amount.add(amount.getAmount()));
  }

  Money minus(Money amount) {
    return money.of(this.amount.subtract(amount.getAmount()));
  }

  Money multiplyBy(BigDecimal factor) {
    return money.of(amount.multiply(factor));
  }

  boolean isNegative() {
    return isBelow(money.of(0));
  }

  boolean isBelow(Money amount) {
    return this.amount.compareTo(amount.getAmount()) < 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Money money = (Money) o;

    return amount != null ? amount.equals(money.amount) : money.amount == null;
  }

  @Override
  public int hashCode() {
    return amount != null ? amount.hashCode() : 0;
  }

  public static class MoneyBuilder {
    Money of(BigDecimal amount) {
      return new Money(amount);
    }

    public Money of(double amount) {
      return new Money(new BigDecimal(amount, MathContext.DECIMAL64));
    }

  }
}
