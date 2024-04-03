package Helpers;

public class Helpers {
	
	// Método para concatenar los elementos de un array con un delimitador
    public static String implode(String[] array, String delimitador) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            resultado.append(array[i]);
            if (i < array.length - 1) {
                resultado.append(delimitador);
            }
        }
        return resultado.toString();
    }

}
