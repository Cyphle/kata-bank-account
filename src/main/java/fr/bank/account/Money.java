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
    return money.of(this.amount.add(amount.getAmount())).build();
  }
}
