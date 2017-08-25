package fr.bank.account;

public class StatementEntry {
  public static final StatementEntryBuilder statementEntry = new StatementEntryBuilder();
  private Operation operation;
  private Money balanceOfAccountAfterOperation;

  public StatementEntry(Operation operation, Money balanceOfAccountAfterOperation) {
    this.operation = operation;
    this.balanceOfAccountAfterOperation = balanceOfAccountAfterOperation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    StatementEntry that = (StatementEntry) o;

    if (operation != null ? !operation.equals(that.operation) : that.operation != null) return false;
    return balanceOfAccountAfterOperation != null ? balanceOfAccountAfterOperation.equals(that.balanceOfAccountAfterOperation) : that.balanceOfAccountAfterOperation == null;
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

    public StatementEntryBuilder ofOperation(Operation operation) {
      this.operation = operation;
      return this;
    }

    public StatementEntryBuilder withAccountBalanceAfter(Money balanceOfAccountAfterOperation) {
      this.balanceOfAccountAfterOperation = balanceOfAccountAfterOperation;
      return this;
    }

    public StatementEntry createStatementEntry() {
      return new StatementEntry(operation, balanceOfAccountAfterOperation);
    }
  }
}
