package connections;

import java.sql.*;

public class ConnectionFactory {
  /**
   * Cria uma conexão
   * @return a conexão
   */
  public static Connection getConnection() {
    try {
      //Cria a conexão
      Connection con = DriverManager.getConnection(Credentials.getServer(), Credentials.getUser(), Credentials.getPassword());
      //Seleciona o database
      ConnectionFactory.selectDatabase(con);
      return con;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Seleciona o banco de dados
   * @param con - a conexão
   */
  public static void selectDatabase(Connection con) {
    String sql = "USE " + Credentials.getBd();

    try {
      PreparedStatement statement = con.prepareStatement(sql);
      statement.execute();
      statement.close();
    } catch (Exception e) {
      System.out.print(e);
    }
  }
}
