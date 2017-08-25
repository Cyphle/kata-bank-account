package fr.bank.domain.account;

interface Statement {
  void registerStatement(Operation operation, Money balanceOfAccountAfterOperation);

  void giveStatementEntriesInformationTo(AccountStatementFormatter accountStatementFormatter);
}
