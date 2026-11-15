import java.util.ArrayList;
import java.util.List;

public class Bot {

    private List<String> palavrasImproprias = new ArrayList<>();

    public Bot() {
        palavrasImproprias.add("idiota");
        palavrasImproprias.add("burro");
        palavrasImproprias.add("imbecil");
    }

    public boolean analisarComentario(String comentario) {

        String texto = comentario.toLowerCase();

        for (String palavra : palavrasImproprias) {

            if (texto.contains(palavra)) {
                apagarComentario(comentario);
                return false;
            }
        }

        System.out.println("Comentário aprovado!");
        return true;
    }

    public void apagarComentario(String comentario) {
        System.out.println("Comentário removido: " + comentario);
    }
}
