package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FunctionsUtil {
    public static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>) obj);
        }
        return list;
    }

    public static String encriptar(int chave, String texto) {
        StringBuilder textoCifrado = new StringBuilder();
        int tamanhoTexto = texto.length();

        for (int c = 0; c < tamanhoTexto; c++) {
            int letraCifradaASCII = ((int) texto.charAt(c)) + chave;

            while (letraCifradaASCII > 126) {
                letraCifradaASCII -= 94;
            }

            textoCifrado.append((char) letraCifradaASCII);
        }

        return textoCifrado.toString();
    }

    public static String decriptar(int chave, String textoCifrado) {
        StringBuilder texto = new StringBuilder();
        int tamanhoTexto = textoCifrado.length();

        for (int c = 0; c < tamanhoTexto; c++) {
            int letraDecifradaASCII = ((int) textoCifrado.charAt(c)) - chave;

            while (letraDecifradaASCII < 32) {
                letraDecifradaASCII += 94;
            }

            texto.append((char) letraDecifradaASCII);
        }

        return texto.toString();
    }
}
