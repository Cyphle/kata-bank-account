package fr.bank.domain.account;

public interface Statement extends InformationProvider {
  void registerStatement(Operation operation, Money balanceOfAccountAfterOperation);
}
