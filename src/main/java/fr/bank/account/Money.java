package fr.bank.account;

import java.math.BigDecimal;

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
