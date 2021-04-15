package src.table;

import src.util.Functions;
import src.util.Render;

public class Table {
  private String title = "";
  private String[] columns = null;
  private String[][] rows = null;

  /**
   * Cria uma tabela
   * 
   * @param title   - o título da tabela
   * @param columns - um String[] contendo cada coluna
   * @param rows    - um String[][] contendo todas as linhas iniciais
   */
  public Table(String title, String[] columns, String[][] rows) {
    this.title = title;
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

  /**
   * Retornar o tamanho do maior item contido na coluna
   * 
   * @param indexOfColumn - o index da coluna
   * @return um inteiro
   */
  public int getMaxLengthOfColumn(int indexOfColumn) {
    int max = 10;

    for (int x = 0; x <= rows.length - 1; x++) {
      int len = rows[x][indexOfColumn].length();
      if (len > max) {
        max = len;
      }
    }

    return max;
  }

  /**
   * Retornar o tamanho máximo de todas as linhas da tabela
   * 
   * @param add - o valor que será adicionado ao tamanho máximo
   * @return um inteiro
   */
  public int getMaxLengthOfRow(int add) {
    int max = 0;

    for (int x = 0; x <= rows[0].length - 1; x++) {
      max += getMaxLengthOfColumn(x) + add;
    }

    return max;
  }

  /**
   * Renderiza a tabela
   */
  public void renderTable() {
    int qtdOfLines = getMaxLengthOfRow(5);
    int defaultQtdOfLines = 40;

    if (qtdOfLines < defaultQtdOfLines) {
      qtdOfLines = defaultQtdOfLines;
    }

    System.out.println("\n|" + Render.renderLine("[ " + this.title + " ]", qtdOfLines - 1) + "|");

    for (int x = 0; x <= rows.length - 1; x++) {
      for (int y = 0; y <= rows[x].length - 1; y++) {
        int max = getMaxLengthOfColumn(y);
        String format = "%-" + (max + 5) + "s";
        if (x == 0) {
          System.out.printf(format, "| " + rows[x][y]);
        } else {
          System.out.printf(format, "| " + rows[x][y]);
        }
      }

      if (x == 0) {
        System.out.println("|\n|" + Render.renderLine("[ Itens: " + (rows.length - 1) + " ]", qtdOfLines - 1) + "|");
      } else {
        System.out.println("|");
      }
    }

    System.out.println("|" + Render.renderLine(qtdOfLines - 1) + "|");
  }
}
