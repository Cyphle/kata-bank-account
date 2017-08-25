package fr.bank.account;

import fr.bank.account.exceptions.AllowedOverdraftExceededException;
import fr.bank.account.exceptions.NegativeAmountNotAllowedException;
import fr.bank.date.DateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;

import static fr.bank.account.Money.money;
import static fr.bank.account.Operation.operation;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
  /*
    Note :
      - getCurrentBalance should be removed after refacto
      - CurrentBalance will be accessible through a getHistory method which accept a visitor and gives everything needed
      - BankStatement will be injected in account and mocked here
   */
  private static final MathContext DECIMAL_64 = MathContext.DECIMAL64;
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

  @Test
  public void should_have_zero_money_if_no_deposit_has_been_made() throws Exception {
    assertThat(account.getBalance()).isEqualTo(money.of(0));
  }

  @Test
  public void should_have_amount_of_money_when_making_a_deposit() throws Exception {
    account.deposit(money.of(100));
    assertThat(account.getBalance()).isEqualTo(money.of(100));
  }

  @Test
  public void should_have_total_deposits_of_money_when_making_multiple_deposits() throws Exception {
    account.deposit(money.of(100));
    account.deposit(money.of(200));
    assertThat(account.getBalance()).isEqualTo(money.of(300));
  }

  @Test(expected = NegativeAmountNotAllowedException.class)
  public void should_not_be_possible_to_do_a_negative_deposit() throws Exception {
    account.deposit(money.of(-100));
  }

  @Test
  public void should_have_amount_of_money_when_withdrawal_of_amount_is_done() throws Exception {
    account.deposit(money.of(100));
    assertThat(account.withdraw(money.of(50))).isEqualTo(money.of(50));
    assertThat(account.getBalance()).isEqualTo(money.of(50));
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
            operation.atDate(LocalDate.of(2017, 8, 24)).ofAmount(money.of(100)).create(),
            money.of(100));
    assertThat(account.getBalance()).isEqualTo(money.of(100));
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
    assertThat(account.getBalance()).isEqualTo(money.of(50));


  }
}
