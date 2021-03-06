package fr.bank.domain.account;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Operation {
  public static final OperationBuilder operation = new OperationBuilder();
  private final LocalDate operationDate;
  private final Money amount;

  private Operation(LocalDate operationDate, Money amount) {
    this.operationDate = operationDate;
    this.amount = amount;
  }

  public BigDecimal getAmount() {
    return amount.getAmount();
  }

  public LocalDate getOperationDate() {
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

  public static class OperationBuilder {
    private LocalDate operationDate;
    private Money amount;

    public OperationBuilder atDate(LocalDate operationDate) {
      this.operationDate = operationDate;
      return this;
    }

    public OperationBuilder ofAmount(Money amount) {
      this.amount = amount;
      return this;
    }

    public Operation create() {
      return new Operation(operationDate, amount);
    }
  }
}
