package src.negocio;
import src.dados.*;

public class ReservaPassagem {

    private Cidade [] listaDeCidades = new Cidade[500];
    private int contCidade = 0;
    private Cliente [] listaDeClientes = new Cliente[500];
    private int contCliente = 0;


    public Cidade[] getListaDeCidades() {
        return listaDeCidades;
    }

    public void setListaDeCidades(Cidade[] listaDeCidades) {
        this.listaDeCidades = listaDeCidades;
    }

    public Cliente[] getListaDeClientes() {
        return listaDeClientes;
    }

    public void setListaDeClientes(Cliente[] listaDeClientes) {
        this.listaDeClientes = listaDeClientes;
    }

    public void cadastrarCidade(Cidade cidade){
        listaDeCidades[contCidade] = cidade;
        contCidade++; 
    }

    public void cadastrarCliente(Cliente cliente){
        listaDeClientes[contCliente] = cliente;
        contCliente++;
    }

    public void reservarIda(Cliente cliente, Reserva reserva){
        
        for(int i = 0 ; i < contCliente ; i++){
            if(listaDeClientes[i].getCpf() == cliente.getCpf()){
                listaDeClientes[i].reservarIda(reserva);
            }
        }

    }

    public void reservarVolta(Cliente cliente, Reserva ida, Reserva volta){

        for(int i = 0; i < contCliente ; i++){
            if(listaDeClientes[i].getCpf() == cliente.getCpf()){
                listaDeClientes[i].reservarVolta(ida, volta);
            }
        }
    }

    public Reserva [] mostrarReservas(long cpfCliente){

        for(int i = 0 ; i < contCliente ; i++){
            if(listaDeClientes[i].getCpf() == cpfCliente){
                return listaDeClientes[i].getReservas();
            }
        }
        
        return null;
    }

    public Cliente [] mostrarClientes(){
        return listaDeClientes;
    }

    public Cidade [] mostrarCidades(){
        return listaDeCidades;
    }


}
