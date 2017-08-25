package fr.bank.account;

import java.util.List;

public interface Statement {
  void registerStatement(Operation operation, Money balanceOfAccountAfterOperation);

  List<StatementEntry> getStatementEntries();
}
