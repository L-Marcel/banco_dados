package util;

public class Default {
 /**
  * Retorna um valor padrão caso o valor anterior seja null
  * 
  * @param def   - a valor padrão
  * @param value - o valor a ser analisado
  * @return um dos dois valores
  */
 public static <T> T defaultValue(T def, T value) {
  T t = null;

  if (value != null) {
   t = value;
  } else {
   t = def;
  }

  return t;
 }
}
