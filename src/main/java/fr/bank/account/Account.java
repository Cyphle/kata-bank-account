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

  public void deposit(Money amount) throws NegativeAmountNotAllowException {
    if (amount.isNegative())
      throw new NegativeAmountNotAllowException();
    currentBalance = currentBalance.add(amount);
  }

  public Money withdraw(BigDecimal amount) {
    Money withdrawal = money.of(amount);
    currentBalance = currentBalance.minus(withdrawal);
    return withdrawal;
  }
}
