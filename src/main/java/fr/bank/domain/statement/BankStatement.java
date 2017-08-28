package fr.bank.domain.statement;

import fr.bank.domain.account.Money;
import fr.bank.domain.account.Operation;
import fr.bank.domain.account.Statement;
import fr.bank.domain.account.StatementFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static fr.bank.domain.account.Money.money;
import static fr.bank.domain.statement.StatementEntry.statementEntry;

public class BankStatement implements Statement {
  final List<StatementEntry> statements;

  public BankStatement() {
    statements = new ArrayList<>();
  }

  @Override
  public void registerStatementEntry(Operation operation, Money balanceOfAccountAfterOperation) {
    statements.add(statementEntry.ofOperation(operation).withAccountBalanceAfter(balanceOfAccountAfterOperation).create());
  }

  @Override
  public Money getLastBalance() {
    Optional<StatementEntry> lastEntry = statements.stream()
            .sorted((entryOne, entryTwo) -> entryTwo.getOperationDate().compareTo(entryOne.getOperationDate()))
            .findAny();
    if (lastEntry.isPresent())
      return lastEntry.get().getBalanceAfterOperation();
    return money.of(0);
  }

  @Override
  public void giveStatementInformationTo(StatementFormatter statementFormatter) {
    List<StatementEntry> sortedStatements = statements
            .stream()
            .sorted((entryOne, entryTwo) -> entryTwo.getOperationDate().compareTo(entryOne.getOperationDate()))
            .collect(Collectors.toList());
    for (StatementEntry entry : sortedStatements)
      entry.giveStatementInformationTo(statementFormatter);
  }
}
