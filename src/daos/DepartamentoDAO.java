package daos;

import java.sql.*;

import util.Converter;
import util.Default;
import util.Functions;
import table.Table;

import entities.Departamento;

public class DepartamentoDAO {
     
    public static void adicionar(Connection con, Departamento departamento) {
        String sql = "INSERT INTO Departamento "
            + "(numero,nome,cpf_gerente,data_ini_gerente) VALUES (?,?,?,?)";
    
        try {
          PreparedStatement statement = con.prepareStatement(sql);
    
          statement.setInt(1, departamento.getNumero());
          statement.setString(2, departamento.getNome());
          statement.setString(3, departamento.getCpf_gerente());
          statement.setDate(4, Date.valueOf(departamento.getData_ini_gerente().toString()));
    
          statement.execute();
          statement.close();
    
          System.out.println(departamento.toString("Criado"));
        } catch (Exception e) {
          System.out.print(e);
        }
      };
      public static Table selecionar(Connection con, String atrs[], String where) {
        String[] defaultAtrs = { "numero", "nome", "cpf_gerente", "data_ini_gerente"};
    
        if (atrs.length == 0) {
          atrs = defaultAtrs;
        }
    
        String sql = "SELECT " + (Converter.arrayToString(atrs, ",", false)) + " FROM Empregado " + where;
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
      public static Departamento selecionar(Connection con, int numero) {
        String sql = "SELECT * FROM Departamento WHERE numero=?";
    
        try {
          PreparedStatement statement = con.prepareStatement(sql);
          statement.setInt(1, numero);
    
          ResultSet response = statement.executeQuery();
    
          Departamento departamento = new Departamento();
    
          while (response.next()) {
            empregado.setNumero(response.getInt("numero"));
            empregado.setNome(response.getString("nome"));
            empregado.setCpf_gerente(response.getLong("cpf_gerente"));
            empregado.setData_ini_gerente(response.getDate("data_ini_gerente"));
          }
    
          response.close();
          statement.close();
    
          return departamento;
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      };
      public static Departamento remover(Connection con, int numero) {
        String sql = "DELETE FROM Departamento WHERE numero=?";
        Departamento departamento = selecionar(con, numero);
    
        if (departamento.getNumero()) {
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
      }      
      public static Departamento atualizar(Connection con, int numero, Departamento newDepartamento) {
        String sql = "UPDATE Departamento SET numero=?, nome=?, cpf_gerente=?, data_ini_gerente=?";
    
        Departamento departamento = selecionar(con, numero);
    
        if (departamento.getNumero()) {
          throw new RuntimeException("Departamento não existe!");
        }
    
        try {
          PreparedStatement statement = con.prepareStatement(sql);
    
          statement.setInt(1, newDepartamento.getNumero());
          statement.setString(2, newDepartamento.getNome());
          statement.setString(3, newDepartamento.getCpf_gerente());
          statement.setDate(4, Date.valueOf(newDepartamento.getData_ini_gerente().toString()));
    
          statement.setInt(10, numero);
    
          statement.executeUpdate();
          statement.close();
    
          Departamento departamentoS = selecionar(con, newDepartamento.getNumero());
    
          System.out.println(departamento.toString("Antigo"));
          System.out.println(departamentoS.toString("Atualizado"));
    
          return departamento;
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
}
