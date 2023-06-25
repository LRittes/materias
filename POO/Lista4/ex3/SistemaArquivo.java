import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SistemaArquivo {
    Map<String, ArrayList<Arquivo>> diretorios = new HashMap<>();

    public void criarDiretorio(String nome) {
        diretorios.put(nome, new ArrayList<Arquivo>());
    }

    public void criarVideo(String nome, String diretorio, Integer qualidade) {
        Video aux = new Video(nome);
        aux.setQualidade(qualidade);
        diretorios.get(diretorio).add(aux);
    }

    public void criarMusica(String nome, String diretorio, int duracao) {
        Musica aux = new Musica(nome);
        aux.setDuracao(duracao);
        diretorios.get(diretorio).add(aux);
    }

    public void criarDocumento(String nome, String diretorio, String texto) {
        Documento aux = new Documento(nome);
        aux.setTexto(texto);
        diretorios.get(diretorio).add(aux);
    }

    @Override
    public String toString() {
        String str = "";
        for (String diretorio : diretorios.keySet()) {
            str += "\n" + diretorio;
            for (Arquivo arquivo : diretorios.get(diretorio)) {
                str += "\n" + arquivo.toString();
            }
        }
        return str;
    }
}
