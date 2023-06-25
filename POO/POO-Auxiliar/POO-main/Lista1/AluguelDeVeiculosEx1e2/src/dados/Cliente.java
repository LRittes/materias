package dados;

public class Cliente {

    private String nome;
    private String cpf;
    private Endereco endereco;
    private Veiculo veiculoAlugado;

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Veiculo getVeiculoAlugado() {
        return veiculoAlugado;
    }

    public void setVeiculoAlugado(Veiculo veiculoAlugado) {
        this.veiculoAlugado = veiculoAlugado;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        if (endereco == null) {
            if (other.endereco != null)
                return false;
        } else if (!endereco.equals(other.endereco))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (veiculoAlugado != other.veiculoAlugado)
            return false;
        return true;
    }

    public String toString() {
        return "Nome=" + nome + "\nCpf=" + cpf + "\n---Endereco---\n" + endereco +"\n---Veiculo Alugado---\n"
                + veiculoAlugado;
    }



}
