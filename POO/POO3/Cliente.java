import java.util.List;

public class Cliente {
    private int cpf;
    private String name;
    private String endereco;
    private int telefone;
    private List<Reserva> reservas;

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Cliente(int cpf, String name, String endereco, int telefone) {
        this.cpf = cpf;
        this.name = name;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public void reservaIda(Reserva reserva) {
        reservas.add(reserva);
    }

    public void reservaVolta(Reserva reserva) {
        reservas.add(reserva);
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String toString() {
        return "nome: " + getName() + " cpf: " + getCpf() + " telefone: " + getTelefone() + " endereço: "
                + getEndereco();

    }
}