package daos;

import java.sql.*;

import entities.Empregado;
import util.Converter;
import util.Default;
import util.Functions;
import table.Table;

//Adicionar, remover, atualizar, selecionar
public class EmpregadoDAO {
  /**
   * Adiciona um empregado no banco de dados
   * 
   * @param con       - a conexão com o banco de dados
   * @param empregado - o empregado a ser adicionado
   */
  public static void adicionar(Connection con, Empregado empregado) {
    String sql = "INSERT INTO Empregado "
        + "(cpf,pnome,unome,data_nasc,endereco,salario,sexo,numero_dep,cpf_supervisor) VALUES (?,?,?,?,?,?,?,?,?)";

    try {
      PreparedStatement statement = con.prepareStatement(sql);

      statement.setString(1, empregado.getCpf());
      statement.setString(2, empregado.getPnome());
      statement.setString(3, empregado.getUnome());
      statement.setDate(4, Date.valueOf(empregado.getDataNasc().toString()));
      statement.setString(5, empregado.getEndereco());
      statement.setDouble(6, empregado.getSalario());
      statement.setString(7, empregado.getSexo());
      statement.setString(8, Integer.toString(empregado.getNumeroDep()));
      statement.setString(9, empregado.getCpfSupervisor());

      statement.execute();
      statement.close();

      System.out.println(empregado.toString("Criado"));
    } catch (Exception e) {
      System.out.print(e);
    }
  };

  /**
   * Deleta um único empregado do banco de dados
   * 
   * @param con - a conexão com o banco de dados
   * @param cpf - uma String contendo o cpf
   * @return o empregado deletado
   */
  public static Empregado remover(Connection con, String cpf) {
    String sql = "DELETE FROM Empregado WHERE cpf=?";
    Empregado empregado = selecionar(con, cpf);

    if (empregado.getCpf().equals("00000000000")) {
      throw new RuntimeException("Empregado não existe!!!");
    }

    try {
      PreparedStatement statement = con.prepareStatement(sql);

      statement.setString(1, cpf);

      statement.execute();
      statement.close();

      System.out.println(empregado.toString("Deletado"));

      return empregado;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Atualiza um único empregado do banco de dados
   * 
   * @param con          - a conexão com o banco de dados
   * @param cpf          - uma String contendo o cpf do antigo empregado
   * @param newEmpregado - o Empregado que será colocando sobre antigo Empregado
   * @return o empregado antigo
   */
  public static Empregado atualizar(Connection con, String cpf, Empregado newEmpregado) {
    String sql = "UPDATE Empregado SET cpf=?, pnome=?, unome=?, data_nasc=?, endereco=?, salario=?, "
    + "sexo=?, numero_dep=?, cpf_supervisor=? WHERE cpf=?";

    Empregado empregado = selecionar(con, cpf);

    if (empregado.getCpf().equals("00000000000")) {
      throw new RuntimeException("Empregado não existe!!!");
    }

    try {
      PreparedStatement statement = con.prepareStatement(sql);

      statement.setString(1, newEmpregado.getCpf());
      statement.setString(2, newEmpregado.getPnome());
      statement.setString(3, newEmpregado.getUnome());
      statement.setDate(4, Date.valueOf(newEmpregado.getDataNasc().toString()));
      statement.setString(5, newEmpregado.getEndereco());
      statement.setDouble(6, newEmpregado.getSalario());
      statement.setString(7, newEmpregado.getSexo());
      statement.setString(8, Integer.toString(newEmpregado.getNumeroDep()));
      statement.setString(9, newEmpregado.getCpfSupervisor());

      statement.setString(10, cpf);

      statement.executeUpdate();
      statement.close();

      Empregado empregadoRes = selecionar(con, newEmpregado.getCpf());

      System.out.println(empregado.toString("Antigo"));
      System.out.println(empregadoRes.toString("Atualizado"));

      return empregado;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Seleciona um conjunto de empregados do banco de dados
   * 
   * @param con   - a conexão com o banco de dados
   * @param atrs  - um array do tipo String[] com os atributos que devem ser
   *              coletados (se não conter nenhum item, todos os atributos serão
   *              coletados)
   * @param where - uma String com o filtro utilizado para selecionar os
   *              empregados (se for uma String vazia, retornará todos os
   *              empregados)
   * @return uma tabela
   */
  public static Table selecionar(Connection con, String atrs[], String where) {
    String[] defaultAtrs = { "cpf", "pnome", "unome", "data_nasc", "endereco", "salario", "sexo", "numero_dep",
        "cpf_supervisor" };

    if (atrs.length == 0) {
      atrs = defaultAtrs;
    }

    String sql = "SELECT " + (Converter.arrayToString(atrs, ",", false)) + " FROM Empregado " + where;
    String[][] rows = new String[0][0];
    Table table = new Table("Empregados Selecionados", atrs, rows);

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
   * Seleciona um único empregado do banco de dados
   * 
   * @param con - a conexão com o banco de dados
   * @param cpf - uma String contendo o cpf
   * @return o empregado
   */
  public static Empregado selecionar(Connection con, String cpf) {
    String sql = "SELECT * FROM Empregado WHERE cpf=?";

    try {
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(1, cpf);

      ResultSet response = statement.executeQuery();

      Empregado empregado = new Empregado();

      while (response.next()) {
        empregado.setCpf(response.getLong("cpf"));
        empregado.setPnome(response.getString("pnome"));
        empregado.setUnome(response.getString("unome"));
        empregado.setDataNasc(response.getDate("data_nasc"));
        empregado.setEndereco(response.getString("endereco"));
        empregado.setSalario(response.getDouble("salario"));
        empregado.setSexo(response.getString("sexo"));
        empregado.setNumeroDep(response.getInt("numero_dep"));
        empregado.setCpfSupervisor(response.getString("cpf_supervisor"));
      }

      response.close();
      statement.close();

      return empregado;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  };
};
