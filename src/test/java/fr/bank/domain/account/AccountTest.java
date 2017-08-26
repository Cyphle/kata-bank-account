package fr.bank.domain.account;

import fr.bank.domain.account.exceptions.AllowedOverdraftExceededException;
import fr.bank.domain.account.exceptions.NegativeAmountNotAllowedException;
import fr.bank.domain.date.DateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static fr.bank.domain.account.Money.money;
import static fr.bank.domain.account.Operation.operation;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
  private Account account;
  @Mock
  private Statement bankStatement;
  @Mock
  private DateService bankDateService;

  @Before
  public void setUp() throws Exception {
    account = new Account(bankStatement, bankDateService);
    Mockito.doReturn(LocalDate.of(2017, 8, 24)).when(bankDateService).dateOfToday();
  }

  @Test(expected = NegativeAmountNotAllowedException.class)
  public void should_not_be_possible_to_do_a_negative_deposit() throws Exception {
    account.deposit(money.of(-100));
  }

  @Test(expected = AllowedOverdraftExceededException.class)
  public void should_not_withdraw_when_overdraft_is_exceeded() throws Exception {
    account.withdraw(money.of(401));
  }

  @Test(expected = NegativeAmountNotAllowedException.class)
  public void should_not_allow_withdrawal_of_negative_amount() throws Exception {
    account.withdraw(money.of(-100));
  }

  @Test
  public void should_add_a_deposit_statement_entry_when_doing_a_deposit() throws Exception {
    account.deposit(money.of(100));

    verify(bankStatement).registerStatement(
            operation
                    .atDate(LocalDate.of(2017, 8, 24))
                    .ofAmount(money.of(100))
                    .create(),
            money.of(100));
  }

  @Test
  public void should_add_two_deposit_statement_entries_when_doing_two_deposits() throws Exception {
    account.deposit(money.of(100));
    account.deposit(money.of(200));

    verify(bankStatement).registerStatement(
            operation
                    .atDate(LocalDate.of(2017, 8, 24))
                    .ofAmount(money.of(200))
                    .create(),
            money.of(300));
  }

  @Test
  public void should_add_a_withdrawal_statement_entry_when_doing_a_withdrawal() throws Exception {
    account.deposit(money.of(100));

    assertThat(account.withdraw(money.of(50))).isEqualTo(money.of(50));
    verify(bankStatement).registerStatement(
            operation
                    .atDate(LocalDate.of(2017, 8, 24))
                    .ofAmount(money.of(-50))
                    .create(),
            money.of(50));
  }

  @Test
  public void should_add_two_withdrawal_statement_entries_when_doing_two_withdrawal() throws Exception {
    account.deposit(money.of(100));

    assertThat(account.withdraw(money.of(50))).isEqualTo(money.of(50));
    assertThat(account.withdraw(money.of(20))).isEqualTo(money.of(20));
    verify(bankStatement).registerStatement(
            operation
                    .atDate(LocalDate.of(2017, 8, 24))
                    .ofAmount(money.of(-20))
                    .create(),
            money.of(30));

  }
}
