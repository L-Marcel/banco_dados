package src.connections;

import java.sql.*;

public class ConnectionFactory {
 public Connection getConnection() {
  try {
   return DriverManager.getConnection(Credentials.getServer(), Credentials.getUser(), Credentials.getPassword());
  } catch (SQLException e) {
   throw new RuntimeException(e);
  }
 }

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
