package fr.bank.domain.account;

import fr.bank.domain.account.exceptions.AllowedOverdraftExceededException;
import fr.bank.domain.account.exceptions.NegativeAmountNotAllowedException;

public interface Account {
  void deposit(Money amountToDeposit) throws NegativeAmountNotAllowedException;

  Money withdraw(Money amountToWithdraw) throws AllowedOverdraftExceededException, NegativeAmountNotAllowedException;
}
