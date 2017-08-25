package fr.bank.domain.account;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface AccountStatementFormatter {
  void addStatementEntry(LocalDate operationDate, BigDecimal amount, BigDecimal amount1);
}
