package fr.bank.domain.account;

public interface InformationProvider {
  void giveStatementInformationTo(StatementFormatter statementFormatter);
}
