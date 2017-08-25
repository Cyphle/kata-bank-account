package fr.bank.infra.printer;

import java.util.List;

public class ConsolePrinter {
  public void print(List<String> listToPrint) {
    System.out.println("Date of operation : Type of operation : Amount of operation : Account balance after operation");
    for (String toPrint : listToPrint)
      System.out.println(toPrint);
  }
}
