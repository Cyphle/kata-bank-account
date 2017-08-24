package fr.bank.account;

import java.math.BigDecimal;
import java.math.MathContext;

import static fr.bank.account.MoneyBuilder.money;

public class Account {
  private Money currentBalance;

  public Account() {
    currentBalance = money.of(new BigDecimal(0, MathContext.DECIMAL64));
  }

  public BigDecimal getCurrentBalance() {
    return currentBalance.getAmount();
  }

  public void deposit(BigDecimal amount) {
    currentBalance = currentBalance.add(money.of(amount));
  }
}
