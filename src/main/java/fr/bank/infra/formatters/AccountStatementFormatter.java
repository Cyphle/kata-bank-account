package fr.bank.infra.formatters;

import fr.bank.domain.account.StatementFormatter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountStatementFormatter implements StatementFormatter {
  private final ArrayList<String> statements;

  public AccountStatementFormatter() {
    statements = new ArrayList<>();
  }

  public List<String> getStatementEntries() {
    Collections.reverse(statements);
    return statements;
  }

  @Override
  public void addStatementEntry(LocalDate operationDate, BigDecimal operationAmount, BigDecimal balanceAfterOperation) {
    String formattedStatementEntry = operationDate.format(DateTimeFormatter.ISO_DATE) + " : ";
    if (operationAmount.compareTo(new BigDecimal(0)) < 0)
      formattedStatementEntry += "WITHDRAWAL : " + operationAmount.toString();
    else
      formattedStatementEntry += "DEPOSIT : " + operationAmount.toString();
    formattedStatementEntry += " : " + balanceAfterOperation.toString();
    statements.add(formattedStatementEntry);
  }
}
