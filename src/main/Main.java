package main;

import connections.*;
import daos.EmpregadoDAO;
import entities.Empregado;
import menus.Menu;

import java.sql.*;
import java.util.Scanner;

public class Main {
  public static final Scanner input = new Scanner(System.in);
  public static void main(String[] args) {
    try {
      Connection con = new ConnectionFactory().getConnection();
      ConnectionFactory.selectDatabase(con);
      con.close();

      Menu menu = new Menu();
      menu.menu(input);
    } catch (Exception e) {
      System.out.print(e);
    }
  };
};