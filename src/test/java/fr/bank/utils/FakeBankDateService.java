package fr.bank.utils;

import fr.bank.domain.date.BankDateService;

import java.time.LocalDate;

public class FakeBankDateService extends BankDateService {
  @Override
  protected LocalDate getTodayDate() {
    return LocalDate.of(2017, 8, 24);
  }
}
