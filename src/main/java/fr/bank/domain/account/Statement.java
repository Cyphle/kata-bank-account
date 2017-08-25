package fr.bank.domain.account;

public interface Statement {
  void registerStatement(Operation operation, Money balanceOfAccountAfterOperation);

  void giveStatementEntriesInformationTo(AccountStatementFormatter accountStatementFormatter);
}
