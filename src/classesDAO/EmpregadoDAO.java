package src.classesDAO;

import java.io.Console;
import java.sql.*;
import java.util.Arrays;

import src.Empregado;
import src.util.Converter;
import src.util.Default;
import src.util.Functions;
import src.table.Table;

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
        + "(cpf,pnome,unome,data_nasc,endereco,salario,sexo,numero_dep,cpf_supervisor) " + "VALUES (?,?,?,?,?,?,?,?,?)";

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

      System.out.print(empregado.toString("Criado"));
    } catch (Exception e) {
      System.out.print(e);
    }
  };

  public static void remover(Connection con, String cpf){
    String sql = "DELETE FROM Empregado WHERE cpf=?";

    try {
      PreparedStatement statement = con.prepareStatement(sql);

      statement.setString(1, cpf);

      statement.execute();
      statement.close();

      System.out.print(empregado.toString("Criado"));
    } catch (Exception e) {
      System.out.print(e);
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
   * Seleciona um conjunto de empregados do banco de dados
   * 
   * @param con   - a conexão com o banco de dados
   * @param atrs  - um array do tipo String[] com os atributos que devem ser
   *              coletados (se não conter nenhum item, todos os atributos serão
   *              coletados)
   * @param where - uma String com o filtro utilizado para selecionar os
   *              empregados (se for uma String vazia, retornará todos os
   *              empregados)
   */
  public static Empregado selecionar(Connection con, String cpf) {
    String sql = "SELECT * FROM Empregado WHERE cpf=?";

    try {
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(1, cpf);

      ResultSet response = statement.executeQuery();

      String _cpf = response.getString("cpf");
      String pnome = reponse.getString("pnome"); 
      String unome = response.getString("unome"); 
      String dataNasc = response.getDate("data_nasc").toString(); 
      String endereco = response.getString("endereco"); 
      Double salario = response.getDouble("salario");
      String sexo = response.getString("sexo");
      int numeroDep = response.getInt("numero_dep");
      String cpfSupervisor = response.getString("cpf_supervisor");
      Empregado empregado = new Empregado(_cpf, pnome, unome, dataNasc, endereco, salario, sexo, numeroDep, cpfSupervisor);
      
      response.close();
      statement.close();
      return empregado;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  };
};
