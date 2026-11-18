
package com.example.demo;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class BotModerador {

    private ArrayList<String> palavrasImproprias = new ArrayList<>();

    public BotModerador() {

        palavrasImproprias.add("idiota");
        palavrasImproprias.add("palavraofensiva1");
        palavrasImproprias.add("burro");
        palavrasImproprias.add("palavraofensiva2");

    }

    public String moderarComentario(String comentario) {

        if (comentario == null || comentario.trim().isEmpty()) {
            return null;
        }

        if (comentario.length() > 500) {
            return null;
        }

        String comentarioModerado = comentario;

        for (String palavra : palavrasImproprias) {

            String mascara = "";

            for (int i = 0; i < palavra.length(); i++) {
                mascara += "*";
            }

            comentarioModerado = comentarioModerado.replaceAll(
                "(?i)\\b" + Pattern.quote(palavra) + "\\b",
                mascara
            );
        }

        return comentarioModerado;
    }
}
