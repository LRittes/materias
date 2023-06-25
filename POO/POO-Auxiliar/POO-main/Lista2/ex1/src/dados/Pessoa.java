package dados;

public class Pessoa {

    private int  idPessoa;
    private String nome;
    private String cpf;
    private int idade;
    private String cidade;
    
    public Pessoa(int idPessoa, String nome, String cpf, int idade, String cidade) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.cidade = cidade;
    }

    public Pessoa(){}

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


    public String toString() {
        return  cidade + ", " + cpf  +  ", " + idade + ", " + nome;
    }


}
