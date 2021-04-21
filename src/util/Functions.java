package util;

import java.util.Arrays;

public class Functions {
  /**
    * Adiciona uma string em um array existente.
    * 
    * @param array - uma array do tipo String[]
    * @param item  - uma string
    * @return uma copia do array original com o seu novo item
    */
  public static String[] addInArray(String[] array, String item) {
    //Cria uma copia do Array com um tamanho maior
    String[] newArray = Arrays.copyOf(array, array.length + 1);
    newArray[newArray.length - 1] = item;
    return newArray;
  }

  /**
    * Adiciona um array do tipo String[] em um array existente.
    * 
    * @param array - uma array do tipo String[][]
    * @param item  - uma array do tipo String[]
    * @return uma copia do array original com o seu novo item
    */
  public static String[][] addInArray(String[][] array, String[] item) {
    //Cria uma copia do Array com um tamanho maior
    String[][] newArray = Arrays.copyOf(array, array.length + 1);
    newArray[newArray.length - 1] = item;
    return newArray;
  }
}
