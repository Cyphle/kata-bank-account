package fr.bank.account;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.assertj.core.api.Assertions.*;

public class AccountTest {
    /*
    - As a bank client
    - I want to make a deposit in my account
    - In order to save money
     */

  @Test
  public void should_have_zero_if_no_deposit_has_been_made() throws Exception {
    Account account = new Account();
    assertThat(account.getCurrentBalance()).isEqualTo(new BigDecimal(0, MathContext.DECIMAL64));
  }
}
