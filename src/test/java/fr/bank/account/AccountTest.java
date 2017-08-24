package fr.bank.account;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {
  private Account account;
  /*
    - As a bank client
    - I want to make a deposit in my account
    - In order to save money
     */

  @Before
  public void setUp() throws Exception {
    account = new Account();
  }

  @Test
  public void should_have_zero_if_no_deposit_has_been_made() throws Exception {
    assertThat(account.getCurrentBalance()).isEqualTo(new BigDecimal(0, MathContext.DECIMAL64));
  }

  @Test
  public void should_have_amount_when_making_a_deposit() throws Exception {
    account.deposit(new BigDecimal(100, MathContext.DECIMAL64));
    assertThat(account.getCurrentBalance()).isEqualTo(new BigDecimal(100, MathContext.DECIMAL64));
  }

  @Test
  public void should_have_total_deposit_when_making_multiple_deposits() throws Exception {
    account.deposit(new BigDecimal(100, MathContext.DECIMAL64));
    account.deposit(new BigDecimal(200, MathContext.DECIMAL64));
    assertThat(account.getCurrentBalance()).isEqualTo(new BigDecimal(300, MathContext.DECIMAL64));
  }
}
