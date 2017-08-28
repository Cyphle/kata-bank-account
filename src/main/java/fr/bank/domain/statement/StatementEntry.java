package fr.bank.domain.statement;

import fr.bank.domain.account.InformationProvider;
import fr.bank.domain.account.Money;
import fr.bank.domain.account.Operation;
import fr.bank.domain.account.StatementFormatter;

import java.time.LocalDate;

public class StatementEntry implements InformationProvider {
  public static final StatementEntryBuilder statementEntry = new StatementEntryBuilder();
  private final Operation operation;
  private final Money balanceOfAccountAfterOperation;

  private StatementEntry(Operation operation, Money balanceOfAccountAfterOperation) {
    this.operation = operation;
    this.balanceOfAccountAfterOperation = balanceOfAccountAfterOperation;
  }

  public LocalDate getOperationDate() {
    return operation.getOperationDate();
  }

  public Money getBalanceAfterOperation() {
    return balanceOfAccountAfterOperation;
  }

  @Override
  public void giveStatementInformationTo(StatementFormatter statementFormatter) {
    statementFormatter.addStatementEntry(operation.getOperationDate(), operation.getAmount(), balanceOfAccountAfterOperation.getAmount());
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

  public static class StatementEntryBuilder {
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

    public StatementEntry create() {
      return new StatementEntry(operation, balanceOfAccountAfterOperation);
    }
  }
}
