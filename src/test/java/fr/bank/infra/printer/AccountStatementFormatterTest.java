package fr.bank.infra.printer;

import fr.bank.domain.account.Account;
import fr.bank.domain.account.BankStatement;
import fr.bank.infra.formatters.AccountStatementFormatter;
import fr.bank.utils.FakeBankDateService;
import org.junit.Before;
import org.junit.Test;

import static fr.bank.domain.account.Money.money;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountStatementFormatterTest {
  private Account account;
  private AccountStatementFormatter accountFormatter;

  @Before
  public void setUp() throws Exception {
    account = new Account(new BankStatement(), new FakeBankDateService());
    accountFormatter = new AccountStatementFormatter();
  }

  @Test
  public void should_add_no_statement_entries_when_there_are_no_entries() throws Exception {
    account.giveStatementInformationTo(accountFormatter);
    assertThat(accountFormatter.getStatementEntries()).isEmpty();
  }

  @Test
  public void should_add_a_deposit_statement_entry_when_there_is_a_deposit() throws Exception {
    account.deposit(money.of(100));
    account.giveStatementInformationTo(accountFormatter);
    assertThat(accountFormatter.getStatementEntries()).containsExactly("2017-08-24 : DEPOSIT : 100 : 100");
  }

  @Test
  public void should_add_a_withdrawal_statement_entry_when_there_is_a_withdrawal() throws Exception {
    account.withdraw(money.of(50));
    account.giveStatementInformationTo(accountFormatter);
    assertThat(accountFormatter.getStatementEntries()).containsExactly("2017-08-24 : WITHDRAWAL : -50 : -50");
  }

  @Test
  public void should_get_statement_entries_of_account_ready_to_be_printed() throws Exception {
    account.deposit(money.of(100));
    account.withdraw(money.of(50));
    account.deposit(money.of(100));

    account.giveStatementInformationTo(accountFormatter);

    assertThat(accountFormatter.getStatementEntries()).containsExactly(
            "2017-08-24 : DEPOSIT : 100 : 150",
            "2017-08-24 : WITHDRAWAL : -50 : 50",
            "2017-08-24 : DEPOSIT : 100 : 100"
    );
  }
}
