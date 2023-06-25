package dados;

public class Pessoa implements Comparable<Pessoa> {
    private String name;
    private int idade;
    private int cpf;
    private String cidade;

    public String getNome() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getCid() {
        return cidade;
    }

    public void setCid(String cidade) {
        this.cidade = cidade;
    }

    public String toString() {
        return this.name
                + " / " + this.idade
                + " / " + this.cpf
                + " / " + this.cidade;
    }

    @Override
    public int compareTo(Pessoa p) {
        return this.getNome().compareTo(p.getNome());
    }
}
