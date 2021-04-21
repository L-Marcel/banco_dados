package main;

import menus.Menu;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    try {
      Scanner input = new Scanner(System.in, "UTF-8");
      Menu menu = new Menu();
      menu.render(input);
    } catch (Exception e) {
      System.out.print(e);
    }
  };
};