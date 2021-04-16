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
      /*Empregado empregado = new Empregado("000.111.222-50", "Lucas", "Marcel",
      "2002-10-02", "Macau - RN", 1500.00, "m", 1);
      System.out.print(empregado.toString() + "\n\n"); 
      EmpregadoDAO.adicionar(con, empregado);
      */
      
      String[] atrs = {};
      Table table = EmpregadoDAO.selecionar(con, atrs, "");
      con.close();
    } catch (Exception e) {
      System.out.print(e);
    }
  };
};