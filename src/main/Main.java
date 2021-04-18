package main;

import menus.Menu;

import java.util.Scanner;

public class Main {
  public static final Scanner input = new Scanner(System.in);
  public static void main(String[] args) {
    try {
      Menu menu = new Menu();
      menu.menu(input);
    } catch (Exception e) {
      System.out.print(e);
    }
  };
};