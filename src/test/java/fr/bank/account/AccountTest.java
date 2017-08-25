package fr.bank.account;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static fr.bank.account.MoneyBuilder.money;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {
  /*
    Note :
      - getCurrentBalance should be removed after refacto
      - CurrentBalance will be accessible through a getHistory method which accept a visitor and gives everything needed
   */
  private static final MathContext DECIMAL_64 = MathContext.DECIMAL64;
  private Account account;

  @Before
  public void setUp() throws Exception {
    account = new Account();
  }

  @Test
  public void should_have_zero_money_if_no_deposit_has_been_made() throws Exception {
    assertThat(account.getCurrentBalance()).isEqualTo(new BigDecimal(0, DECIMAL_64));
  }

  @Test
  public void should_have_amount_of_money_when_making_a_deposit() throws Exception {
    account.deposit(money.of(100));
    assertThat(account.getCurrentBalance()).isEqualTo(new BigDecimal(100, DECIMAL_64));
  }

  @Test
  public void should_have_total_deposits_of_money_when_making_multiple_deposits() throws Exception {
    account.deposit(money.of(100));
    account.deposit(money.of(200));
    assertThat(account.getCurrentBalance()).isEqualTo(new BigDecimal(300, DECIMAL_64));
  }

  @Test(expected = NegativeAmountNotAllowedException.class)
  public void should_not_be_possible_to_do_a_negative_deposit() throws Exception {
    account.deposit(money.of(-100));
  }

  @Test
  public void should_have_amount_of_money_when_withdrawal_of_amount_is_done() throws Exception {
    account.deposit(money.of(100));
    assertThat(account.withdraw(money.of(50))).isEqualTo(money.of(new BigDecimal(50, DECIMAL_64)));
    assertThat(account.getCurrentBalance()).isEqualTo(new BigDecimal(50, DECIMAL_64));
  }

  @Test(expected = AllowedOverdraftExceededException.class)
  public void should_not_withdraw_when_overdraft_is_exceeded() throws Exception {
    account.withdraw(money.of(401));
  }

  @Test(expected = NegativeAmountNotAllowedException.class)
  public void should_not_allow_withdrawal_of_negative_amount() throws Exception {
    account.withdraw(money.of(-100));
  }
}
