package fr.bank.account;

import fr.bank.date.DateService;

import java.util.ArrayList;
import java.util.List;

public class Statement {
  List<Operation> operations;
  private DateService dateService;

  public Statement(DateService dateService) {
    this.dateService = dateService;
    operations = new ArrayList<>();
  }

  public void addDepositOperationOf(Money depositAmount, Money accountBalanceAfterOperation) {
    operations.add(new Operation(dateService.dateOfToday(), depositAmount, accountBalanceAfterOperation));
  }
}
