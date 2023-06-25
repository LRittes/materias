import java.util.ArrayList;
import java.util.List;

public class Equipe<T> {
    
    private String nome;
    List<T> lista = new ArrayList<>();

    public Equipe(int qtdAlunos){
        for(int i=0; i<qtdAlunos; i++){
            lista.add(null);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }

    public String toString() {
        return "Equipe [lista=" + lista + ", nome=" + nome + "]";
    }

}
