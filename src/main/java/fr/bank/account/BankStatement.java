package fr.bank.account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static fr.bank.account.StatementEntry.statementEntry;

public class BankStatement implements Statement {
  public List<StatementEntry> statements;

  public BankStatement() {
    statements = new ArrayList<>();
  }

  @Override
  public List<StatementEntry> getStatementEntries() {
    return Collections.unmodifiableList(statements);
  }

  @Override
  public void registerStatement(Operation operation, Money balanceOfAccountAfterOperation) {
    statements.add(statementEntry.ofOperation(operation).withAccountBalanceAfter(balanceOfAccountAfterOperation).create());
  }
}
