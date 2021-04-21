package daos;

import java.sql.*;

import connections.ConnectionFactory;
import util.Converter;
import util.Default;
import util.Functions;
import table.Table;

import entities.Dependente;

public class DependenteDAO {
  /**
   * Adiciona um dependente no banco de dados
   * 
   * @param dependente - o departamento a ser adicionado
   */
  public static void adicionar(Dependente dependente) {
    Connection con = ConnectionFactory.getConnection();
    String sql = "INSERT INTO Dependente (nome,cpf_empregado,data_nasc,sexo,parentesco) VALUES (?,?,?,?,?)";

    try {
      PreparedStatement statement = con.prepareStatement(sql);

      statement.setString(1, dependente.getNome());
      statement.setString(2, dependente.getCpfEmpregado());
      statement.setDate(3, Date.valueOf(dependente.getDataNasc().toString()));
      statement.setString(4, dependente.getSexo());
      statement.setString(5, dependente.getParentesco());

      statement.execute();
      statement.close();
      con.close();
      System.out.println(dependente.toString("Criado"));
    } catch (Exception e) {
      System.out.print(e);
    }
  };

  /**
   * Deleta um único dependente do banco de dados
   * 
   * @param cpfEmpregado - uma String contendo o cpf do empregado relacionado ao
   *                     dependente
   * @param nome         - uma String contendo o nome do dependente
   * @return o dependente deletado
   */
  public static Dependente remover(String cpfEmpregado, String nome) {
    Connection con = ConnectionFactory.getConnection();
    String sql = "DELETE FROM Dependente WHERE cpf_empregado=? AND nome=?";
    Dependente dependente = selecionar(cpfEmpregado, nome);

    if (dependente.getCpfEmpregado().equals("00000000000")) {
      throw new RuntimeException("Dependente não existe!");
    }

    try {
      PreparedStatement statement = con.prepareStatement(sql);

      statement.setString(1, cpfEmpregado);
      statement.setString(2, nome);

      statement.execute();
      statement.close();
      con.close();
      System.out.println(dependente.toString("Deletado"));

      return dependente;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  };

  /**
   * Atualiza um único dependente do banco de dados
   * 
   * @param cpfEmpregado  - uma String contendo o cpf do empregado do antigo dependente
   * @param nome          - um inteiro contendo o nome do antigo dependente
   * @param newDependente - o Dependente que será colocando sobre antigo
   *                        Dependente
   * @return o dependente antigo
   */
  public static Dependente atualizar(String cpfEmpregado, String nome, Dependente newDependente) {
    Connection con = ConnectionFactory.getConnection();
    String sql = "UPDATE Dependente SET nome=?, cpf_empregado=?, data_nasc=?, sexo=?, parentesco=? WHERE cpf_empregado=?";

    Dependente dependente = selecionar(cpfEmpregado, nome);

    if (dependente.getCpfEmpregado().equals("00000000000")) {
      throw new RuntimeException("Dependente não existe!!!");
    }

    try {
      PreparedStatement statement = con.prepareStatement(sql);

      statement.setString(1, dependente.getNome());
      statement.setString(2, dependente.getCpfEmpregado());
      statement.setDate(3, Date.valueOf(dependente.getDataNasc().toString()));
      statement.setString(4, dependente.getSexo());
      statement.setString(5, dependente.getParentesco());

      statement.setString(6, cpfEmpregado);

      statement.executeUpdate();
      statement.close();
      con.close();
      Dependente dependenteRes = selecionar(newDependente.getCpfEmpregado(), newDependente.getNome());

      System.out.println(dependente.toString("Antigo"));
      System.out.println(dependenteRes.toString("Atualizado"));

      return dependente;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Seleciona um conjunto de dependentes do banco de dados
   * 
   * @param atrs  - um array do tipo String[] com os atributos que devem ser
   *              coletados (se não conter nenhum item, todos os atributos serão
   *              coletados)
   * @param where - uma String com o filtro utilizado para selecionar os
   *              dependentes (se for uma String vazia, retornará todos os
   *              dependentes)
   * @return uma tabela
   */
  public static Table selecionar(String atrs[], String where) {
    Connection con = ConnectionFactory.getConnection();
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
        String[] row = new String[0]; //Cria a linha da tabela
        for (int x = 0; x <= defaultAtrs.length - 1; x++) {
          String value = ""; //O valor da coluna a ser adicionada na linha
          for (int y = 0; y <= atrs.length - 1; y++) {
            if (defaultAtrs[x].equals(atrs[y])) { //Se o nome da coluna estiver na lista padrão
              //Tenta pegar pegar uma String com o valor da coluna
              //Se for null ou uma String vazia retorna "null"
              value = Default.defaultValue("null", response.getString(defaultAtrs[x]));

              //Adiciona as colunas na linha
              row = Functions.addInArray(row, value);
            }
          }
        }

        //Adiciona as linhas na tabela
        table.addRow(row);
      }

      response.close();
      statement.close();
      con.close();
      table.renderTable();
      return table;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  };

  /**
   * Seleciona um único dependente do banco de dados
   * 
   * @param cpfEmpregado - uma String contendo o cpf do empregado do dependente
   * @param nome         - uma String contendo o nome do dependente
   * @return o dependente
   */
  public static Dependente selecionar(String cpfEmpregado, String nome) {
    Connection con = ConnectionFactory.getConnection();
    String sql = "SELECT * FROM Dependente WHERE cpf_empregado=? AND nome=?";

    try {
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(1, cpfEmpregado);
      statement.setString(2, nome);

      ResultSet response = statement.executeQuery();

      Dependente dependente = new Dependente();

      while (response.next()) {
        dependente.setNome(response.getString("nome"));
        dependente.setCpfEmpregado(response.getLong("cpf_empregado"));
        dependente.setDataNasc(response.getDate("data_nasc"));
        dependente.setSexo(response.getString("sexo"));
        dependente.setParentesco(response.getString("parentesco"));
      }

      response.close();
      statement.close();
      con.close();
      return dependente;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  };
}
