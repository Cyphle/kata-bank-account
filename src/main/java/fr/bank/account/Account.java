package fr.bank.account;

import java.math.BigDecimal;
import java.math.MathContext;

import static fr.bank.account.MoneyBuilder.money;

public class Account {
  public static final MathContext DECIMAL_64 = MathContext.DECIMAL64;
  private Money currentBalance;

  public Account() {
    currentBalance = money.of(new BigDecimal(0, DECIMAL_64));
  }

  public BigDecimal getCurrentBalance() {
    return currentBalance.getAmount();
  }

  public void deposit(BigDecimal amount) throws NegativeAmountNotAllowException {
    if (amount.compareTo(new BigDecimal(0, DECIMAL_64)) < 0)
      throw new NegativeAmountNotAllowException();
    currentBalance = currentBalance.add(money.of(amount));
  }
}
