package fr.bank.domain.account;

interface Statement extends InformationProvider {
  void registerStatement(Operation operation, Money balanceOfAccountAfterOperation);
}
