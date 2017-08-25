package fr.bank.account;

import fr.bank.date.DateService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static fr.bank.account.Operation.operation;
import static fr.bank.account.StatementEntry.statementEntry;

public class Statement {
  List<Operation> operations;
  private DateService dateService;
  public List<StatementEntry> statements;

  public Statement(DateService dateService) {
    this.dateService = dateService;
    operations = new ArrayList<>();
    statements = new ArrayList<>();
  }

  public void addDepositOperationOf(Money depositAmount) {
    operations.add(operation.atDate(dateService.dateOfToday()).ofAmount(depositAmount).build());
  }

  public void addWithdrawalOperationOf(Money withdrawalAmount) {
    operations.add(operation.atDate(dateService.dateOfToday()).ofAmount(withdrawalAmount.multiplyBy(new BigDecimal(-1))).build());
  }

  public void registerStatement(Operation operation, Money balanceOfAccountAfterOperation) {
    statements.add(statementEntry.ofOperation(operation).withAccountBalanceAfter(balanceOfAccountAfterOperation).createStatementEntry());
  }
}
