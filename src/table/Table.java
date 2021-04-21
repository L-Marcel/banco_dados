package table;

import util.Functions;
import util.Render;

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
   * @author Marcel
   */
  public void renderTable() {
    //Antes de tudo: essa droga deu muito trabalho

    //Pega o tamanho da maior linha
    //Minimo: 40
    int qtdOfLines = getMaxLengthOfRow(5);
    int defaultQtdOfLines = 40;
    if (qtdOfLines < defaultQtdOfLines) {
      qtdOfLines = defaultQtdOfLines;
    }

    //Renderiza o titulo
    //O -1 é para o "|" não passar
    System.out.println("\n|" + Render.renderLine("[ " + this.title + " ]", qtdOfLines - 1) + "|");

    //Roda a matriz formada por colunas e linhas
    for (int x = 0; x <= rows.length - 1; x++) { //A linha
      for (int y = 0; y <= rows[x].length - 1; y++) { //A coluna
        //O tamanho da maior coluna
        //Ps: considera todas as linhas
        int max = getMaxLengthOfColumn(y); 

        //O formato a ser imprimido
        //O %- indica que a formatação será para a esquerda
        //O max + 5 é o tamanho da maior coluna +5
        String format = "%-" + (max + 5) + "s";
        //Pega o tamanho da linha atual
        int rlen = rows[x].length; 
        //Pega o tamanho da maior linha da tabela e adicona
        //5 vezes o tamanho da atual 
        //(pq é dificil 5 colunas não darem maior ou igual a 40)
        int newLen = getMaxLengthOfRow(5 * rlen); 

        //Se o tamanho da maior linha + 5 for menor que 40 
        //e o tamanho da maior + 5 vezes o tamanho da atual for
        //menor ou igual a 40
        if(getMaxLengthOfRow(5) < qtdOfLines &&  newLen >= qtdOfLines){
          //Significa que o tamnho da linha é maior ou igual a  40
          //Então o formado (tamanho de cada coluna)
          //Vai ser newLen/rlen
          //Indicando que cada coluna vai ter um tamanho proporcional
          //Ao tamanho máximo da linha
          format = "%-" + (newLen/rlen) + "s";
        }else if(newLen < qtdOfLines){
          //Se o tamanho máximo da linha for menor que 40
          //Tem q considerar o tamanho máximo 40
          //Para não estragar o titulo
          format = "%-" + (qtdOfLines/rlen) + "s";
        }
          
        System.out.printf(format, "| " + rows[x][y]);
      }

      if (x == 0) {
        System.out.println("|\n|" + Render.renderLine("[ Itens: " + (rows.length - 1) + " ]", qtdOfLines - 1) + "|");
      } else {
        System.out.println("|");
      }
    }

    System.out.println("|" + Render.renderLine(qtdOfLines - 1) + "|\n");
  }
}
