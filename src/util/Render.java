package util;

import java.util.*;

public class Render {
 private static int qtd = 35;

 /**
  * Renderiza uma linha com um título no meio
  * 
  * @param title - o título
  * @return a linha
  */
 public static String renderLine(String title) {
  int _qtd = qtd - title.length();
  String incrementValue = "";
  char line = '-';

  if ((_qtd % 2) == 1) {
   incrementValue = String.valueOf(line);
   _qtd--;
  }

  char[] chars = new char[_qtd / 2];
  Arrays.fill(chars, line);
  return (new String(chars)) + (incrementValue) + (title) + (new String(chars));
 };

 /**
  * Renderiza uma linha com um título e uma quantidade especifica de "-"
  * 
  * @param title - o título
  * @param qtd   - a quantidade de "-"
  * @return a linha
  */
 public static String renderLine(String title, int qtd) {
  int _qtd = qtd - title.length();
  String incrementValue = "";
  char line = '-';

  if ((_qtd % 2) == 1) {
   incrementValue = String.valueOf(line);
   _qtd--;
  }

  char[] chars = new char[_qtd / 2];
  Arrays.fill(chars, line);
  return (new String(chars)) + (incrementValue) + (title) + (new String(chars));
 };

 /**
  * Renderiza uma linha com uma quantidade especifica de "-"
  * 
  * @param qtd - a quantidade de "-"
  * @return a linha
  */
 public static String renderLine(int qtd) {
  char line = '-';
  char[] chars = new char[qtd];
  Arrays.fill(chars, line);
  return new String(chars);
 }

 /**
  * Renderiza uma linha
  * 
  * @return a linha
  */
 public static String renderLine() {
  char line = '-';
  char[] chars = new char[qtd];
  Arrays.fill(chars, line);
  return new String(chars);
 }

 public static int getQtd() {
  return qtd;
 }

 public static void setQtd(int qtd) {
  Render.qtd = qtd;
 };
}
