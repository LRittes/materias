
import java.util.ArrayList;
import java.util.List;

public class Equipe<T> {
    private String nome;
    private List<T> time = new ArrayList<T>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<T> getEquipe() {
        return time;
    }

    public void setEquipe(List<T> time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Equipe: " +
                "\nNome: " + nome +
                "\nMebros" + time;
    }
}
