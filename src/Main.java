package src;

import src.classesDAO.EmpregadoDAO;
import src.connections.*;
import src.table.Table;

import java.sql.*;

public class Main {
  public static void main(String[] args) {
    try {
      Connection con = new ConnectionFactory().getConnection();
      ConnectionFactory.selectDatabase(con);

      /* Empregado empregado = new Empregado("000.111.222-36", "Lucas", "Marcel",
      "2002-10-02", "Macau - RN", 1500.00, "m", 1);
      System.out.print(empregado.toString() + "\n\n"); Empregados.adicionar(con,
      empregado); aaaaaaaa

      String[] atrs = { "cpf", "pnome", "unome", "salario", "sexo", "endereco", "cpf_supervisor" };
      Table table = EmpregadoDAO.selecionar(con, atrs, "WHERE salario > 2000");*/

      con.close();
    } catch (Exception e) {
      System.out.print(e);
    }
  };
};