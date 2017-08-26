package fr.bank.domain.account;

import fr.bank.domain.account.exceptions.AllowedOverdraftExceededException;
import fr.bank.domain.account.exceptions.NegativeAmountNotAllowedException;
import fr.bank.domain.date.DateService;

import java.math.BigDecimal;

import static fr.bank.domain.account.Money.money;
import static fr.bank.domain.account.Operation.operation;

public class Account {
  private static final Money MAXIMUM_OVERDRAFT = money.of(-400);
  private Money currentBalance;
  private final Statement statement;
  private final DateService dateService;

  public Account(Statement statement, DateService dateService) {
    this.statement = statement;
    this.dateService = dateService;
    currentBalance = money.of(0);
  }

  public void deposit(Money amountToDeposit) throws NegativeAmountNotAllowedException {
    if (amountToDeposit.isNegative())
      throw new NegativeAmountNotAllowedException();

    currentBalance = currentBalance.plus(amountToDeposit);
    statement.registerStatement(
            operation.atDate(dateService.dateOfToday())
                    .ofAmount(amountToDeposit)
                    .create(),
            currentBalance);
  }

  public Money withdraw(Money amountToWithdraw) throws AllowedOverdraftExceededException, NegativeAmountNotAllowedException {
    if (amountToWithdraw.isNegative())
      throw new NegativeAmountNotAllowedException();

    if (currentBalance.minus(amountToWithdraw).isBelow(MAXIMUM_OVERDRAFT))
      throw new AllowedOverdraftExceededException();

    currentBalance = currentBalance.minus(amountToWithdraw);
    statement.registerStatement(
            operation.atDate(dateService.dateOfToday())
                    .ofAmount(amountToWithdraw.multiplyBy(new BigDecimal(-1)))
                    .create(),
            currentBalance);
    return amountToWithdraw;
  }

  public void giveStatementInformationTo(StatementFormatter statementFormatter) {
    statement.giveStatementEntriesInformationTo(statementFormatter);
  }
}
