package src.classesDAO;

import java.sql.*;
import java.util.Arrays;

import src.Empregado;
import src.util.Converter;
import src.util.Default;
import src.util.Functions;
import src.table.Table;

public class EmpregadoDAO {
 public static void adicionar(Connection con, Empregado empregado) {
  String sql = "INSERT INTO Empregado " + "(cpf,pnome,unome,data_nasc,endereco,salario,sexo,numero_dep,cpf_supervisor) "
    + "VALUES (?,?,?,?,?,?,?,?,?)";

  try {
   PreparedStatement statement = con.prepareStatement(sql);

   statement.setString(1, empregado.getCpf());
   statement.setString(2, empregado.getPnome());
   statement.setString(3, empregado.getUnome());
   statement.setString(4, empregado.getDataNasc().toString());
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

 public static Table selecionar(Connection con, String atrs[], String where) {
  String sql = "SELECT " + (Converter.arrayToString(atrs, ",", false)) + " FROM Empregado " + where;

  String[] defaultAtrs = { "cpf", "pnome", "unome", "dataNasc", "endereco", "salario", "sexo", "numeroDep",
    "cpfSupervisor" };

  String[][] rows = new String[0][0];
  Table table = new Table("Empregados Selecionados", "Empregado", atrs, rows);

  try {
   PreparedStatement statement = con.prepareStatement(sql);
   ResultSet response = statement.executeQuery();

   while (response.next()) {
    String[] row = new String[0];
    for (int x = 0; x < defaultAtrs.length - 1; x++) {
     String value = "";
     if (Arrays.stream(atrs).anyMatch(defaultAtrs[x]::equals)) {
      value = Default.defaultValue("null", response.getString(defaultAtrs[x]));
      row = Functions.addInArray(row, value);
     }
    }
    table.addRow(row);
   }

   response.close();
   statement.close();

   System.out.print(table.toString());
   return table;
  } catch (SQLException e) {
   throw new RuntimeException(e);
  }
 };
};

