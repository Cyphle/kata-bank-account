package fr.bank.infra.printer;

import fr.bank.domain.account.AccountFormatter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConsoleAccountFormatter implements AccountFormatter {
  private List<String> statements;

  public ConsoleAccountFormatter() {
    statements = new ArrayList<>();
  }

  public List<String> getStatementEntries() {
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
