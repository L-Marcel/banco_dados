package connections;

public class Credentials {
 private static final String user = "5fMzvWRdbK";
 private static final String password = "We3jxXNiw7";
 private static final String bd = "5fMzvWRdbK";
 private static final String server = "jdbc:mysql://remotemysql.com:3306";

 public static String getUser() {
  return user;
 }

 public static String getPassword() {
  return password;
 }

 public static String getBd() {
  return bd;
 }

 public static String getServer() {
  return server;
 }
}
