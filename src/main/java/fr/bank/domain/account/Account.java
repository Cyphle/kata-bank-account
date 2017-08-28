package fr.bank.domain.account;

import fr.bank.domain.account.exceptions.AllowedOverdraftExceededException;
import fr.bank.domain.account.exceptions.NegativeAmountNotAllowedException;
import fr.bank.domain.date.DateService;

import java.math.BigDecimal;

import static fr.bank.domain.account.Money.money;
import static fr.bank.domain.account.Operation.operation;

public class Account implements InformationProvider {
  private static final Money MAXIMUM_OVERDRAFT = money.of(-400);
  private final Statement statement;
  private final DateService dateService;

  public Account(Statement statement, DateService dateService) {
    this.statement = statement;
    this.dateService = dateService;
  }

  public void deposit(Money amountToDeposit) throws NegativeAmountNotAllowedException {
    checkIsPositive(amountToDeposit);

    Money balance = statement.getLastBalance().plus(amountToDeposit);
    statement.registerStatementEntry(
            operation.atDate(dateService.dateOfToday())
                    .ofAmount(amountToDeposit)
                    .create(),
            balance);
  }

  public Money withdraw(Money amountToWithdraw) throws AllowedOverdraftExceededException, NegativeAmountNotAllowedException {
    checkIsPositive(amountToWithdraw);

    if (statement.getLastBalance().minus(amountToWithdraw).isBelow(MAXIMUM_OVERDRAFT))
      throw new AllowedOverdraftExceededException();

    Money balance = statement.getLastBalance().minus(amountToWithdraw);
    statement.registerStatementEntry(
            operation.atDate(dateService.dateOfToday())
                    .ofAmount(amountToWithdraw.multiplyBy(new BigDecimal(-1)))
                    .create(),
            balance);
    return amountToWithdraw;
  }

  @Override
  public void giveStatementInformationTo(StatementFormatter statementFormatter) {
    statement.giveStatementInformationTo(statementFormatter);
  }

  private void checkIsPositive(Money amountToDeposit) throws NegativeAmountNotAllowedException {
    if (amountToDeposit.isNegative())
      throw new NegativeAmountNotAllowedException();
  }
}
