package fr.bank.account;

import java.time.LocalDate;

public class Operation {
  private LocalDate operationDate;
  private Money amount;
  private Money accountBalanceAfterOperation;

  public Operation(LocalDate operationDate, Money amount, Money accountBalanceAfterOperation) {
    this.operationDate = operationDate;
    this.amount = amount;
    this.accountBalanceAfterOperation = accountBalanceAfterOperation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Operation operation = (Operation) o;

    if (operationDate != null ? !operationDate.equals(operation.operationDate) : operation.operationDate != null)
      return false;
    if (amount != null ? !amount.equals(operation.amount) : operation.amount != null) return false;
    return accountBalanceAfterOperation != null ? accountBalanceAfterOperation.equals(operation.accountBalanceAfterOperation) : operation.accountBalanceAfterOperation == null;
  }

  @Override
  public int hashCode() {
    int result = operationDate != null ? operationDate.hashCode() : 0;
    result = 31 * result + (amount != null ? amount.hashCode() : 0);
    result = 31 * result + (accountBalanceAfterOperation != null ? accountBalanceAfterOperation.hashCode() : 0);
    return result;
  }
}
