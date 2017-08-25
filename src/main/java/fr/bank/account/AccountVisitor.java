package fr.bank.account;

public interface AccountVisitor {
  void visit(Account account);

  void visit(Statement bankStatement);

  void visit(StatementEntry statementEntry);

  void visit(Operation operation);
}
