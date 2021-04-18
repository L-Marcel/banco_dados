package daos;

import java.sql.*;

import util.Converter;
import util.Default;
import util.Functions;
import table.Table;

import entities.Departamento;

public class DepartamentoDAO {
  /**
   * Adiciona um departamento no banco de dados
   * 
   * @param con          - a conexão com o banco de dados
   * @param departamento - o departamento a ser adicionado
   */
  public static void adicionar(Connection con, Departamento departamento) {
    String sql = "INSERT INTO Departamento (numero,nome,cpf_gerente,data_ini_gerente) VALUES (?,?,?,?)";

    try {
      PreparedStatement statement = con.prepareStatement(sql);

      statement.setInt(1, departamento.getNumero());
      statement.setString(2, departamento.getNome());
      statement.setString(3, departamento.getCpfGerente());
      statement.setDate(4, Date.valueOf(departamento.getDataIniGerente().toString()));

      statement.execute();
      statement.close();

      System.out.println(departamento.toString("Criado"));
    } catch (Exception e) {
      System.out.print(e);
    }
  };

  /**
   * Deleta um único departamento do banco de dados
   * 
   * @param con    - a conexão com o banco de dados
   * @param numero - um inteiro contendo o numero do departamento
   * @return o departamento deletado
   */
  public static Departamento remover(Connection con, int numero) {
    String sql = "DELETE FROM Departamento WHERE numero=?";
    Departamento departamento = selecionar(con, numero);

    if (departamento.getNumero() == -1) {
      throw new RuntimeException("Departamento não existe!");
    }

    try {
      PreparedStatement statement = con.prepareStatement(sql);

      statement.setInt(1, numero);

      statement.execute();
      statement.close();

      System.out.println(departamento.toString("Deletado"));

      return departamento;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  };

  /**
   * Atualiza um único departamento do banco de dados
   * 
   * @param con             - a conexão com o banco de dados
   * @param numero          - um inteiro contendo o numero do antigo departamento
   * @param newDepartamento - o Departamento que será colocando sobre antigo
   *                        Departamento
   * @return o empregado antigo
   */
  public static Departamento atualizar(Connection con, int numero, Departamento newDepartamento) {
    String sql = "UPDATE Departamento SET numero=?, nome=?, cpf_gerente=?, data_ini_gerente=? WHERE numero=?";

    Departamento departamento = selecionar(con, numero);

    if (departamento.getNumero() == -1) {
      throw new RuntimeException("Departamento não existe!");
    }

    try {
      PreparedStatement statement = con.prepareStatement(sql);

      statement.setInt(1, newDepartamento.getNumero());
      statement.setString(2, newDepartamento.getNome());
      statement.setString(3, newDepartamento.getCpfGerente());
      statement.setDate(4, Date.valueOf(newDepartamento.getDataIniGerente().toString()));

      statement.setInt(5, numero);

      statement.executeUpdate();
      statement.close();

      Departamento departamentoS = selecionar(con, newDepartamento.getNumero());

      System.out.println(departamento.toString("Antigo"));
      System.out.println(departamentoS.toString("Atualizado"));

      return departamento;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  };

  /**
   * Seleciona um conjunto de departamentos do banco de dados
   * 
   * @param con   - a conexão com o banco de dados
   * @param atrs  - um array do tipo String[] com os atributos que devem ser
   *              coletados (se não conter nenhum item, todos os atributos serão
   *              coletados)
   * @param where - uma String com o filtro utilizado para selecionar os
   *              departamentos (se for uma String vazia, retornará todos os
   *              departamentos)
   * @return uma tabela
   */
  public static Table selecionar(Connection con, String atrs[], String where) {
    String[] defaultAtrs = { "numero", "nome", "cpf_gerente", "data_ini_gerente" };

    if (atrs.length == 0) {
      atrs = defaultAtrs;
    }

    String sql = "SELECT " + (Converter.arrayToString(atrs, ",", false)) + " FROM Departamento " + where;
    String[][] rows = new String[0][0];
    Table table = new Table("Departamentos Selecionados", atrs, rows);

    try {
      PreparedStatement statement = con.prepareStatement(sql);
      ResultSet response = statement.executeQuery();

      while (response.next()) {
        String[] row = new String[0];
        for (int x = 0; x <= defaultAtrs.length - 1; x++) {
          String value = "";
          for (int y = 0; y <= atrs.length - 1; y++) {
            if (defaultAtrs[x].equals(atrs[y])) {
              value = Default.defaultValue("null", response.getString(defaultAtrs[x]));
              row = Functions.addInArray(row, value);
            }
          }
        }
        table.addRow(row);
      }

      response.close();
      statement.close();

      table.renderTable();
      return table;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  };

  /**
   * Seleciona um único departamento do banco de dados
   * 
   * @param con    - a conexão com o banco de dados
   * @param numero - uma String contendo o numero do departamento
   * @return o departamento
   */
  public static Departamento selecionar(Connection con, int numero) {
    String sql = "SELECT * FROM Departamento WHERE numero=?";

    try {
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(1, numero);

      ResultSet response = statement.executeQuery();

      Departamento departamento = new Departamento();

      while (response.next()) {
        departamento.setNumero(response.getInt("numero"));
        departamento.setNome(response.getString("nome"));
        departamento.setCpfGerente(response.getLong("cpf_gerente"));
        departamento.setDataIniGenrente(response.getDate("data_ini_gerente"));
      }

      response.close();
      statement.close();

      return departamento;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  };
}
