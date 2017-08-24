package fr.bank.account;

import java.math.BigDecimal;
import java.math.MathContext;

import static fr.bank.account.MoneyBuilder.money;

class Account {
  private static final MathContext DECIMAL_64 = MathContext.DECIMAL64;
  private static final Money MAXIMUM_OVERDRAFT = money.of(-400);
  private Money currentBalance;

  public Account() {
    currentBalance = money.of(new BigDecimal(0, DECIMAL_64));
  }

  BigDecimal getCurrentBalance() {
    return currentBalance.getAmount();
  }

  void deposit(Money amount) throws NegativeAmountNotAllowedException {
    if (amount.isNegative())
      throw new NegativeAmountNotAllowedException();
    currentBalance = currentBalance.add(amount);
  }

  Money withdraw(Money amount) throws AllowedOverdraftExceededException, NegativeAmountNotAllowedException {
    if (amount.isNegative())
      throw new NegativeAmountNotAllowedException();

    if (currentBalance.minus(amount).isBelow(MAXIMUM_OVERDRAFT))
      throw new AllowedOverdraftExceededException();

    currentBalance = currentBalance.minus(amount);
    return amount;
  }
}
