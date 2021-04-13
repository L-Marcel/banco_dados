package src.table;

import src.util.Converter;
import src.util.Functions;
import src.util.Render;

public class Table {
  String title = "";
  String nameOfItem = "";
  String[] columns = null;
  String[][] rows = null;

  /**
   * Cria uma tabela
   * 
   * @param title      - o título da tabela
   * @param nameOfItem - o nome dado a cada item
   * @param columns    - um String[] contendo cada coluna
   * @param rows       - um String[][] contendo todas as linhas iniciais
   */
  public Table(String title, String nameOfItem, String[] columns, String[][] rows) {
    this.title = title;
    this.nameOfItem = nameOfItem;
    this.columns = columns;

    this.rows = Functions.addInArray(rows, this.columns);
  }

  /**
   * Adiciona uma linha na tabela
   * 
   * @param row - a linha
   */
  public void addRow(String[] row) {
    if (row.length == columns.length) {
      this.rows = Functions.addInArray(rows, row);
    }
  }

  @Override
  public String toString() {
    int qtdOfLines = (0 + ": " + this.nameOfItem + "(" + Converter.arrayToString(rows[0], ",", false) + ");\n").length()
        + 10;
    int defaultQtdOfLines = 40;

    if (qtdOfLines < defaultQtdOfLines) {
      qtdOfLines = defaultQtdOfLines;
    }

    if (rows.length > 1) {
      qtdOfLines = (1 + ": " + this.nameOfItem + "(" + Converter.arrayToString(rows[1], ",", false) + ");\n").length()
          + 10;
    }

    String str = Render.renderLine(this.title, qtdOfLines) + "\n";

    for (int x = 0; x <= rows.length - 1; x++) {
      if (x == 0 || x == rows.length - 1) {
        str += x + ": " + this.nameOfItem + "(" + Converter.arrayToString(rows[x], ",", false) + ");\n"
            + Render.renderLine(qtdOfLines) + "\n";
      } else {
        str += x + ": " + this.nameOfItem + "(" + Converter.arrayToString(rows[x], ",", false) + ");\n";
      }
    }
    return str;
  }
}
