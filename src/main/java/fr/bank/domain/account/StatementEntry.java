package fr.bank.domain.account;

public class StatementEntry {
  static final StatementEntryBuilder statementEntry = new StatementEntryBuilder();
  private final Operation operation;
  private final Money balanceOfAccountAfterOperation;

  private StatementEntry(Operation operation, Money balanceOfAccountAfterOperation) {
    this.operation = operation;
    this.balanceOfAccountAfterOperation = balanceOfAccountAfterOperation;
  }

  void giveStatementEntryInformationTo(AccountStatementFormatter accountStatementFormatter) {
    accountStatementFormatter.addStatementEntry(operation.getOperationDate(), operation.getAmount(), balanceOfAccountAfterOperation.getAmount());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    StatementEntry that = (StatementEntry) o;

    return (operation != null ? operation.equals(that.operation) : that.operation == null) && (balanceOfAccountAfterOperation != null ? balanceOfAccountAfterOperation.equals(that.balanceOfAccountAfterOperation) : that.balanceOfAccountAfterOperation == null);
  }

  @Override
  public int hashCode() {
    int result = operation != null ? operation.hashCode() : 0;
    result = 31 * result + (balanceOfAccountAfterOperation != null ? balanceOfAccountAfterOperation.hashCode() : 0);
    return result;
  }

  static class StatementEntryBuilder {
    private Operation operation;
    private Money balanceOfAccountAfterOperation;

    StatementEntryBuilder ofOperation(Operation operation) {
      this.operation = operation;
      return this;
    }

    StatementEntryBuilder withAccountBalanceAfter(Money balanceOfAccountAfterOperation) {
      this.balanceOfAccountAfterOperation = balanceOfAccountAfterOperation;
      return this;
    }

    StatementEntry create() {
      return new StatementEntry(operation, balanceOfAccountAfterOperation);
    }
  }
}
