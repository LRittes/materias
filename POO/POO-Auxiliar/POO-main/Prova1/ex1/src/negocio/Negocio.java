package negocio;

import java.util.ArrayList;
import java.util.List;

import dados.Cliente;
import dados.ClienteOuro;

public class Negocio{

    private List<Cliente> clientes = new ArrayList<Cliente>();

    public void adicionaCliente(Cliente c){
        clientes.add(c);
    }

    public void adicionaClienteOuro(Cliente c){
        clientes.add(c);
    }

    public double totalMensalidades(){
        
        double mensalidadesTotal = 0.0;

        for(int i = 0; i < clientes.size() ; i++){
            mensalidadesTotal += clientes.get(i).getMensalidade();
        }

        return mensalidadesTotal;
    }

    public double totalBeneficios(){

        double beneficiosTotal = 0.0;

        for(int i = 0; i< clientes.size() ; i++){
            beneficiosTotal += clientes.get(i).getBeneficio();
        }

        return beneficiosTotal;
    }

    public List<String> listaNomeClientesOuro(){

        List<String> nomes = new ArrayList<String>();

        for(int i = 0; i < clientes.size() ; i++){
            if(clientes.get(i) instanceof ClienteOuro){
                nomes.add(clientes.get(i).getNome());
            }
        }

        return nomes;
    }

}