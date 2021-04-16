package main;

import connections.*;
import daos.EmpregadoDAO;
import entities.Empregado;

import java.sql.*;

public class Main {
  public static void main(String[] args) {
    try {
      Connection con = new ConnectionFactory().getConnection();
      ConnectionFactory.selectDatabase(con);

      //Cria um empregado chamado Lucas Brito
      Empregado empregado = new Empregado("000.111.222-80", "Lucas", "Brito",
      "2002-10-02", "Macau - RN", 1800.00, "m", 1);

      
      //Adiciona Lucas
      EmpregadoDAO.adicionar(con, empregado);

      //Imprime os empregados com pnome=Lucas
      String[] atrs = {};
      EmpregadoDAO.selecionar(con, atrs, "WHERE pnome='Lucas'");

      //Atualiza o nome, o cpf e o salario
      String oldCpf = empregado.getCpf();
      empregado.setUnome("Marcel"); //Troca o unome para Lucas Marcel
      empregado.setCpf("000.111.222-81"); //Troca o cpf para 000.111.222-81
      empregado.setSalario(3000.00);
      EmpregadoDAO.atualizar(con, oldCpf, empregado);

      //Imprime os empregados com pnome=Lucas
      EmpregadoDAO.selecionar(con, atrs, "WHERE pnome='Lucas'");

      //Remove ele
      EmpregadoDAO.remover(con, "00011122281");

      //Imprime os empregados com pnome=Lucas
      EmpregadoDAO.selecionar(con, atrs, "WHERE pnome='Lucas'");
      con.close();
    } catch (Exception e) {
      System.out.print(e);
    }
  };
};