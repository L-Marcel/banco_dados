package util;

public class Converter {
 /**
  * Converte o cpf para o seu formato numerico
  * 
  * @param cpf - o cpf
  * @return - um long
  */
 public static long cpfStringToLong(String cpf) {
  return Long.parseLong(cpfString(cpf));
 }

 /**
  * Remove os caractéres especiais do cpf
  * 
  * @param cpf - o cpf
  * @return - uma string
  */
 public static String cpfString(String cpf) {
  cpf = cpf.replace("-", "");
  cpf = cpf.replace(".", "");
  return cpf;
 }

 /**
  * Converte um array do tipo String[] para uma única String
  * 
  * @param arr             - o array a ser convertido
  * @param divider         - o divisor usado para separar os itens
  * @param needLastDivider - precisa de um divisor a mais no final?
  * @return - uma string
  */
 public static String arrayToString(String[] arr, String divider, boolean needLastDivider) {
  String str = "";
  for (int i = 0; i <= arr.length - 1; i++) {
   if (i == arr.length - 1 && !needLastDivider) {
    str += arr[i];
   } else {
    str += arr[i] + divider + " ";
   }
  }
  return str;
 }
}
