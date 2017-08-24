package fr.bank.account;

import java.math.BigDecimal;
import java.math.MathContext;

import static fr.bank.account.MoneyBuilder.money;

public class Account {
  private BigDecimal currentBalanceDecimal;
  private Money currentBalance;

  public Account() {
    currentBalanceDecimal = new BigDecimal(0, MathContext.DECIMAL64);
    currentBalance = money.withAmount(new BigDecimal(0, MathContext.DECIMAL64)).build();
  }

  public BigDecimal getCurrentBalance() {
    return currentBalanceDecimal;
  }

  public void deposit(BigDecimal amount) {
    currentBalanceDecimal = currentBalanceDecimal.add(amount);
  }

  public BigDecimal getBalance() {
    return currentBalance.getAmount();
  }
}
