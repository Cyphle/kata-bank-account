package fr.bank.account;

import java.util.ArrayList;
import java.util.List;

import static fr.bank.account.StatementEntry.statementEntry;

public class Statement {
  public List<StatementEntry> statements;

  public Statement() {
    statements = new ArrayList<>();
  }

  public void registerStatement(Operation operation, Money balanceOfAccountAfterOperation) {
    statements.add(statementEntry.ofOperation(operation).withAccountBalanceAfter(balanceOfAccountAfterOperation).createStatementEntry());
  }
}
