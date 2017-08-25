package fr.bank.account;

import java.math.BigDecimal;
import java.math.MathContext;

public class Money {
  public static final MoneyBuilder money = new MoneyBuilder();
  private final BigDecimal amount;

  Money(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public Money add(Money amount) {
    return money.of(this.amount.add(amount.getAmount()));
  }

  public Money minus(Money amount) {
    return money.of(this.amount.subtract(amount.getAmount()));
  }

  public Money multiplyBy(BigDecimal factor) {
    return money.of(amount.multiply(factor));
  }

  public boolean isNegative() {
    return amount.compareTo(new BigDecimal(0, MathContext.DECIMAL64)) < 0;
  }

  public boolean isBelow(Money amount) {
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

  static class MoneyBuilder {

    public Money of(BigDecimal amount) {
      return new Money(amount);
    }

    public Money of(double amount) {
      return new Money(new BigDecimal(amount, MathContext.DECIMAL64));
    }
  }
}
