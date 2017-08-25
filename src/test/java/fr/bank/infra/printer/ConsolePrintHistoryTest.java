package fr.bank.infra.printer;

import fr.bank.domain.account.Account;
import fr.bank.domain.account.BankStatement;
import fr.bank.infra.formatters.AccountStatementFormatter;
import fr.bank.utils.FakeBankDateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static fr.bank.domain.account.Money.money;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConsolePrintHistoryTest {
  @Mock
  private Brush consoleBrush;
  private Printer printer;
  private AccountStatementFormatter accountFormatter;
  private Account account;

  @Before
  public void setUp() throws Exception {
    printer = new Printer(consoleBrush);
    accountFormatter = new AccountStatementFormatter();
    account = new Account(new BankStatement(), new FakeBankDateService());
  }

  @Test
  public void should_print_history_of_account() throws Exception {
    account.deposit(money.of(100));
    account.withdraw(money.of(50));
    account.deposit(money.of(100));

    account.giveStatementInformationTo(accountFormatter);

    printer.print(accountFormatter.getStatementEntries());

    verify(consoleBrush).paint("Date of operation : Type of operation : Amount of operation : Account balance after operation");
    verify(consoleBrush).paint("2017-08-24 : DEPOSIT : 100 : 150");
    verify(consoleBrush).paint("2017-08-24 : WITHDRAWAL : -50 : 50");
    verify(consoleBrush).paint("2017-08-24 : DEPOSIT : 100 : 100");
  }
}
