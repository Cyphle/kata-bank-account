package fr.bank.domain.account;

import java.util.List;

public interface Statement {
  void registerStatement(Operation operation, Money balanceOfAccountAfterOperation);

  List<StatementEntry> getStatementEntries();

  void accept(AccountVisitor visitor);
}
