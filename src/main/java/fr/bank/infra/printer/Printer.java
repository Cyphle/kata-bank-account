package fr.bank.infra.printer;

import java.util.List;

public class Printer {
  private Brush brush;

  public Printer(Brush brush) {
    this.brush = brush;
  }

  public void print(List<String> listToPrint) {
    brush.paint("Date of operation : Type of operation : Amount of operation : Account balance after operation");
    for (String toPrint : listToPrint)
      brush.paint(toPrint);
  }
}
