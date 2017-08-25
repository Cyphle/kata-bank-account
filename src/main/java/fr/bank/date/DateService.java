package fr.bank.date;

import java.time.LocalDate;

public class DateService {
  public LocalDate dateOfToday() {
    return this.getTodayDate();
  }

  protected LocalDate getTodayDate() {
    return LocalDate.now();
  }
}
