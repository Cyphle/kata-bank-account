package fr.bank.infra.date;

import fr.bank.domain.date.DateService;

import java.time.LocalDate;

public class BankDateService implements DateService {
  @Override
  public LocalDate dateOfToday() {
    return this.getTodayDate();
  }

  protected LocalDate getTodayDate() {
    return LocalDate.now();
  }
}
