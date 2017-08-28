package fr.bank.domain.statement;

import fr.bank.domain.date.DateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static fr.bank.domain.account.Money.money;
import static fr.bank.domain.account.Operation.operation;
import static fr.bank.domain.statement.StatementEntry.statementEntry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class BankStatementTest {
  @Mock
  private DateService dateService;
  private BankStatement bankStatement;

  @Before
  public void setUp() throws Exception {
    bankStatement = new BankStatement();
    given(dateService.dateOfToday()).willReturn(LocalDate.of(2017, 8, 24));
  }

  @Test
  public void should_add_a_statement_entry_for_a_deposit() throws Exception {
    bankStatement.registerStatement(
            operation
                    .atDate(dateService.dateOfToday())
                    .ofAmount(money.of(100))
                    .create(),
            money.of(100));
    assertThat(bankStatement.statements).containsExactly(
            statementEntry.ofOperation(operation
                    .atDate(LocalDate.of(2017, 8, 24))
                    .ofAmount(money.of(100))
                    .create()).withAccountBalanceAfter(money.of(100)).create());
  }
}
