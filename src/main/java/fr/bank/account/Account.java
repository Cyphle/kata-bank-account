package fr.bank.account;

import java.math.BigDecimal;
import java.math.MathContext;

public class Account {
  private BigDecimal currentBalance;

  public Account() {
    currentBalance = new BigDecimal(0, MathContext.DECIMAL64);
  }

  public BigDecimal getCurrentBalance() {
    return currentBalance;
  }

  public void deposit(BigDecimal amount) {
    currentBalance = currentBalance.add(amount);
  }
}
