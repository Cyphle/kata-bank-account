package fr.bank.account;

import java.math.BigDecimal;
import java.math.MathContext;

import static fr.bank.account.MoneyBuilder.money;

class Account {
  private static final Money MAXIMUM_OVERDRAFT = money.of(-400);
  private Money currentBalance;

  public Account() {
    currentBalance = money.of(new BigDecimal(0, MathContext.DECIMAL64));
  }

  BigDecimal getCurrentBalance() {
    return currentBalance.getAmount();
  }

  void deposit(Money amountToDeposit) throws NegativeAmountNotAllowedException {
    if (amountToDeposit.isNegative())
      throw new NegativeAmountNotAllowedException();

    currentBalance = currentBalance.add(amountToDeposit);
  }

  Money withdraw(Money amountToWithdraw) throws AllowedOverdraftExceededException, NegativeAmountNotAllowedException {
    if (amountToWithdraw.isNegative())
      throw new NegativeAmountNotAllowedException();

    if (currentBalance.minus(amountToWithdraw).isBelow(MAXIMUM_OVERDRAFT))
      throw new AllowedOverdraftExceededException();

    currentBalance = currentBalance.minus(amountToWithdraw);
    return amountToWithdraw;
  }
}
