package fr.bank.account;

public interface Statement {
  void registerStatement(Operation operation, Money balanceOfAccountAfterOperation);
}
