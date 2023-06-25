package negocio;

import java.util.Arrays;
import dados.Cliente;
import dados.Veiculo;

public class Negocio {

    private Veiculo [] veiculos = new Veiculo[1];
    private int contAlugados = 0;
    private Cliente [] clientes = new Cliente[1];
    private int contClientes = 0;

    public Veiculo[] getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(Veiculo veiculos) {
        for(int j = 0 ; j < contAlugados ; j++){
            this.veiculos[j] = veiculos;
        }
    }

    public int getContAlugados() {
        return contAlugados;
    }

    public void setContAlugados() {
        this.contAlugados = contAlugados + 1;
    }

    public Cliente[] getClientes() {
        return clientes;
    }

    public void setClientes(Cliente clientes) {
        for(int i = 0; i < contClientes; i++){
            this.clientes[i] = clientes;
        }
    }

    public int getContClientes() {
        return contClientes;
    }

    public void setContClientes() {
        this.contClientes = contClientes + 1;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Negocio negocio = (Negocio) o;
        return contAlugados == negocio.contAlugados &&
                contClientes == negocio.contClientes &&
                Arrays.equals(veiculos, negocio.veiculos) &&
                Arrays.equals(clientes, negocio.clientes);
    }

    public String toString() {
        return "\n---Veiculos---\n" + Arrays.toString(veiculos) + "\n---Clientes---\n" + Arrays.toString(clientes);
    }
}
