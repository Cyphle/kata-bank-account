package fr.bank.account;

import fr.bank.date.DateService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Statement {
  List<Operation> operations;
  private DateService dateService;

  public Statement(DateService dateService) {
    this.dateService = dateService;
    operations = new ArrayList<>();
  }

  public void addDepositOperationOf(Money depositAmount) {
    operations.add(new Operation(dateService.dateOfToday(), depositAmount));
  }

  public void addWithdrawalOperationOf(Money withdrawalAmount) {
    operations.add(new Operation(dateService.dateOfToday(), withdrawalAmount.multiplyBy(new BigDecimal(-1))));
  }
}
