package daos;

import java.sql.*;

import entities.Departamento;
import util.Converter;
import util.Default;
import util.Functions;
import table.Table;

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
}
