package fr.bank.account;

import fr.bank.account.exceptions.AllowedOverdraftExceededException;
import fr.bank.account.exceptions.NegativeAmountNotAllowedException;
import fr.bank.date.DateService;

import java.math.BigDecimal;

import static fr.bank.account.Money.money;
import static fr.bank.account.Operation.operation;

class Account {
  private static final Money MAXIMUM_OVERDRAFT = money.of(-400);
  private Money currentBalance;
  private Statement statement;
  private DateService dateService;

  public Account(Statement statement, DateService dateService) {
    this.statement = statement;
    this.dateService = dateService;
    currentBalance = money.of(0);
  }

  Money getBalance() {
    return currentBalance;
  }

  void deposit(Money amountToDeposit) throws NegativeAmountNotAllowedException {
    if (amountToDeposit.isNegative())
      throw new NegativeAmountNotAllowedException();

    currentBalance = currentBalance.plus(amountToDeposit);
    Operation depositOperation = operation.atDate(dateService.dateOfToday()).ofAmount(amountToDeposit).create();
    statement.registerStatement(depositOperation, currentBalance);
  }

  Money withdraw(Money amountToWithdraw) throws AllowedOverdraftExceededException, NegativeAmountNotAllowedException {
    if (amountToWithdraw.isNegative())
      throw new NegativeAmountNotAllowedException();

    if (currentBalance.minus(amountToWithdraw).isBelow(MAXIMUM_OVERDRAFT))
      throw new AllowedOverdraftExceededException();

    currentBalance = currentBalance.minus(amountToWithdraw);
    Operation withdrawalOperation = operation.atDate(dateService.dateOfToday()).ofAmount(amountToWithdraw.multiplyBy(new BigDecimal(-1))).create();
    statement.registerStatement(withdrawalOperation, currentBalance);
    return amountToWithdraw;
  }
}
