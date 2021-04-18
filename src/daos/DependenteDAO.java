package daos;

import java.sql.*;

import util.Converter;
import util.Default;
import util.Functions;
import table.Table;

import entities.Dependente;

public class DependenteDAO {
   
  public static void adicionar(Connection con, Dependente dependente) {
    String sql = "INSERT INTO Dependente (nome,cpf_empregado,data_nasc,sexo,parentesco) VALUES (?,?,?,?,?)";

    try {
      PreparedStatement statement = con.prepareStatement(sql);

      statement.setString(1, dependente.getNome());
      statement.setString(2, dependente.getCpfEmpregado());
      statement.setDate(3, Date.valueOf(dependente.getDataNasc().toString()));
      statement.setString(4, dependente.getSexo());
      statement.setString(4, dependente.getParentesco());

      statement.execute();
      statement.close();

      System.out.println(dependente.toString("Criado"));
    } catch (Exception e) {
      System.out.print(e);
    }
};

    public static Dependente remover(Connection con, String cpfEmpregado) {
    String sql = "DELETE FROM Dependente WHERE cpf=?";
    Dependente dependente = selecionar(con, cpfEmpregado);

    if (dependente.getCpfEmpregado().equals("00000000000")) {
      throw new RuntimeException("Dependente não existe!");
    }

    try {
      PreparedStatement statement = con.prepareStatement(sql);

      statement.setString(2, cpfEmpregado);

      statement.execute();
      statement.close();

      System.out.println(dependente.toString("Deletado"));

      return dependente;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
};

  public static Dependente atualizar(Connection con, String cpfEmpregado, Dependente newDependente) {
    String sql = "UPDATE Dependente SET nome=?, cpf_empregado=?, data_nasc=?, sexo=?, parentesco=? WHERE cpf_empregado=?";

    Dependente dependente = selecionar(con, cpfEmpregado);

    if (dependente.getCpfEmpregado().equals("00000000000")) {
      throw new RuntimeException("Dependente não existe!!!");
    }

    try {
      PreparedStatement statement = con.prepareStatement(sql);

      statement.setString(1, dependente.getNome());
      statement.setString(2, dependente.getCpfEmpregado());
      statement.setDate(3, Date.valueOf(dependente.getDataNasc().toString()));
      statement.setString(4, dependente.getSexo());
      statement.setString(4, dependente.getParentesco());

      statement.setString(10, cpfEmpregado);

      statement.executeUpdate();
      statement.close();

      Dependente dependenteS = selecionar(con, newDependente.getCpfEmpregado());

      System.out.println(dependente.toString("Antigo"));
      System.out.println(dependenteS.toString("Atualizado"));

      return dependente;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  public static Table selecionar(Connection con, String atrs[], String where) {
    String[] defaultAtrs = { "nome", "cpf_empregado", "data_nasc", "sexo", "parentesco" };

    if (atrs.length == 0) {
      atrs = defaultAtrs;
    }

    String sql = "SELECT " + (Converter.arrayToString(atrs, ",", false)) + " FROM Dependente " + where;
    String[][] rows = new String[0][0];
    Table table = new Table("Dependentes Selecionados", atrs, rows);

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

  public static Dependente selecionar(Connection con, String cpfEmpregado) {
    String sql = "SELECT * FROM Dependente WHERE cpf_empregado=?";

    try {
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(2, cpfEmpregado);

      ResultSet response = statement.executeQuery();

      Dependente dependente = new Dependente();

      while (response.next()) {
        dependente.setNome(response.getString("nome"));
        dependente.setCpfEmpregado(response.getLong("cpf_dependente"));
        dependente.setDataNasc(response.getDate("data_nasc"));
        dependente.setSexo(response.getString("sexo"));
        dependente.setParentesco(response.getString("parentesco"));
      }

      response.close();
      statement.close();

      return dependente;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  };
}
