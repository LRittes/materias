package dados;

public class Processo {
    private int id;
    private String nome;

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "Id: " + this.id + "\nNome: " + this.nome;
    }
}