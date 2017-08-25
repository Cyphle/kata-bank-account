package fr.bank.domain.account;

import java.util.ArrayList;
import java.util.List;

import static fr.bank.domain.account.StatementEntry.statementEntry;

public class BankStatement implements Statement {
  public List<StatementEntry> statements;

  public BankStatement() {
    statements = new ArrayList<>();
  }

  @Override
  public void registerStatement(Operation operation, Money balanceOfAccountAfterOperation) {
    statements.add(statementEntry.ofOperation(operation).withAccountBalanceAfter(balanceOfAccountAfterOperation).create());
  }

  @Override
  public void giveStatementEntriesInformationTo(AccountFormatter accountFormatter) {
    for (StatementEntry entry : statements)
      entry.giveStatementEntryInformationTo(accountFormatter);
  }
}
