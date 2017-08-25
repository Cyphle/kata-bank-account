package fr.bank.account;

import fr.bank.date.DateService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static fr.bank.account.Money.money;
import static org.assertj.core.api.Assertions.*;

public class StatementTest {
  /*
    - As a bank client
    - I want to see the history. (operation, date, amount, balance)  of my operations
        - Each operation has a date, an amount and the account balance after the operation
    - In order to check my operations

    statement (== relevé bancaire) : est chargé de logger les transactions
    will need an intermediary element with associate an operation to an account balance
   */
  private Statement statement;
  private DateService dateService;

  @Before
  public void setUp() throws Exception {
    dateService = new FakeDateService();
    statement = new Statement(dateService);
  }

  @Test
  public void should_add_a_deposit_operation() throws Exception {
    statement.addDepositOperationOf(money.of(100));
    assertThat(statement.operations).containsExactly(new Operation(LocalDate.of(2017, 8, 24), money.of(100)));
  }

  @Test
  public void should_add_a_withdrawal_operation() throws Exception {
    statement.addWithdrawalOperationOf(money.of(100));
    assertThat(statement.operations).containsExactly(new Operation(LocalDate.of(2017, 8, 24), money.of(-100)));
  }

  private class FakeDateService extends DateService {
    @Override
    protected LocalDate getTodayDate() {
      return LocalDate.of(2017, 8, 24);
    }
  }
}
