package fr.bank.domain.account;

import fr.bank.domain.date.DateService;
import fr.bank.utils.FakeBankDateService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static fr.bank.domain.account.Money.money;
import static fr.bank.domain.account.Operation.operation;
import static fr.bank.domain.account.StatementEntry.statementEntry;
import static org.assertj.core.api.Assertions.assertThat;

public class BankStatementTest {
  private BankStatement bankStatement;
  private DateService bankDateService;

  @Before
  public void setUp() throws Exception {
    bankDateService = new FakeBankDateService();
    bankStatement = new BankStatement();
  }

  @Test
  public void should_add_a_statement_entry_for_a_deposit() throws Exception {
    bankStatement.registerStatement(new Operation(bankDateService.dateOfToday(), money.of(100)), money.of(100));
    assertThat(bankStatement.statements).containsExactly(
            statementEntry.ofOperation(operation
                    .atDate(LocalDate.of(2017, 8, 24))
                    .ofAmount(money.of(100))
                    .create()).withAccountBalanceAfter(money.of(100)).create());
  }
}
