package fr.bank.infra.printer;

import java.util.List;

class Printer {
  private final Brush brush;

  Printer(Brush brush) {
    this.brush = brush;
  }

  void print(List<String> listToPrint) {
    brush.paint("Date of operation : Type of operation : Amount of operation : Account balance after operation");
    for (String toPrint : listToPrint)
      brush.paint(toPrint);
  }
}
