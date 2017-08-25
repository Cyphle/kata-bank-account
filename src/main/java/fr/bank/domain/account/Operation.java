package fr.bank.domain.account;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Operation {
  public static final OperationBuilder operation = new OperationBuilder();
  private final LocalDate operationDate;
  private final Money amount;

  Operation(LocalDate operationDate, Money amount) {
    this.operationDate = operationDate;
    this.amount = amount;
  }

  BigDecimal getAmount() {
    return amount.getAmount();
  }

  LocalDate getOperationDate() {
    return operationDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Operation operation = (Operation) o;

    return (operationDate != null ? operationDate.equals(operation.operationDate) : operation.operationDate == null) && (amount != null ? amount.equals(operation.amount) : operation.amount == null);
  }

  @Override
  public int hashCode() {
    int result = operationDate != null ? operationDate.hashCode() : 0;
    result = 31 * result + (amount != null ? amount.hashCode() : 0);
    return result;
  }

  static class OperationBuilder {
    private LocalDate operationDate;
    private Money amount;


    OperationBuilder atDate(LocalDate operationDate) {
      this.operationDate = operationDate;
      return this;
    }

    OperationBuilder ofAmount(Money amount) {
      this.amount = amount;
      return this;
    }

    Operation create() {
      return new Operation(operationDate, amount);
    }
  }
}
