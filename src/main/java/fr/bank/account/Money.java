package fr.bank.account;

import java.math.BigDecimal;
import java.math.MathContext;

import static fr.bank.account.MoneyBuilder.money;

public class Money {
  private BigDecimal amount;

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

  public boolean isNegative() {
    return amount.compareTo(new BigDecimal(0, MathContext.DECIMAL64)) < 0;
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
}
