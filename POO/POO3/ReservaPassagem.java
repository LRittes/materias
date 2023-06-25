import java.util.ArrayList;
import java.util.List;

public class ReservaPassagem {
    private List<Cidade> listaDeCidades;
    private List<Cliente> listaDeClientes;

    public void cadastrarCidade(Cidade cidade) {
        listaDeCidades.add(cidade);
    }

    public void cadastrarCliente(Cliente cliente) {
        listaDeClientes.add(cliente);
    }

    public void reservarIda(Cliente cliente, Reserva reserva) {
    }

    public void reservarVolta(Cliente cliente, Reserva reserva) {
    }

    public List<Reserva> mostrarReservas(int cpf) {
        List<Reserva> reservas = new ArrayList<Reserva>();
        for (Cliente cliente : listaDeClientes) {
            int cliCPF = cliente.getCpf();
            var cliReservas = cliente.getReservas();
            reservas = cliCPF == cpf ? cliReservas : null;
        }
        return reservas;
    }

    public List<Cliente> mostrarClientes() {
        return listaDeClientes;
    }

    public List<Cidade> mostrarCidades() {
        return listaDeCidades;
    }

}
