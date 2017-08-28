package fr.bank.domain.account;

public interface Statement extends InformationProvider {
  void registerStatementEntry(Operation operation, Money balanceOfAccountAfterOperation);

  Money getLastBalance();
}
