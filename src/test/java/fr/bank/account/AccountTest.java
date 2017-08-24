package fr.bank.account;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static fr.bank.account.MoneyBuilder.money;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {
  public static final MathContext DECIMAL_64 = MathContext.DECIMAL64;
  private Account account;
  /*
    - As a bank client
    - I want to make a withdrawal from my account
    - In order to retrieve some or all of my savings
     */

  @Before
  public void setUp() throws Exception {
    account = new Account();
  }

  @Test
  public void should_have_zero_if_no_deposit_has_been_made() throws Exception {
    assertThat(account.getCurrentBalance()).isEqualTo(new BigDecimal(0, DECIMAL_64));
  }

  @Test
  public void should_have_amount_when_making_a_deposit() throws Exception {
    account.deposit(new BigDecimal(100, DECIMAL_64));
    assertThat(account.getCurrentBalance()).isEqualTo(new BigDecimal(100, DECIMAL_64));
  }

  @Test
  public void should_have_total_deposit_when_making_multiple_deposits() throws Exception {
    account.deposit(new BigDecimal(100, DECIMAL_64));
    account.deposit(new BigDecimal(200, DECIMAL_64));
    assertThat(account.getCurrentBalance()).isEqualTo(new BigDecimal(300, DECIMAL_64));
  }

  @Test(expected = NegativeAmountNotAllowException.class)
  public void should_not_be_possible_to_add_a_negative_deposit() throws Exception {
    account.deposit(new BigDecimal(-100, DECIMAL_64));
  }

  @Test
  public void should_have_amount_when_withdrawal_is_made() throws Exception {
    account.deposit(new BigDecimal(100, DECIMAL_64));
    assertThat(account.withdraw(new BigDecimal(50, DECIMAL_64))).isEqualTo(money.of(new BigDecimal(50, DECIMAL_64)));
    assertThat(account.getCurrentBalance()).isEqualTo(new BigDecimal(50, DECIMAL_64));
  }
}
