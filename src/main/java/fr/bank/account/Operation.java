package fr.bank.account;

import java.time.LocalDate;

import static fr.bank.account.Money.money;

public class Operation {
  public static final OperationBuilder operation = new OperationBuilder();
  private LocalDate operationDate;
  private Money amount;

  public Operation(LocalDate operationDate, Money amount) {
    this.operationDate = operationDate;
    this.amount = amount;
  }

  public Money getAmount() {
    return money.of(amount);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Operation operation = (Operation) o;

    if (operationDate != null ? !operationDate.equals(operation.operationDate) : operation.operationDate != null)
      return false;
    return amount != null ? amount.equals(operation.amount) : operation.amount == null;
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
