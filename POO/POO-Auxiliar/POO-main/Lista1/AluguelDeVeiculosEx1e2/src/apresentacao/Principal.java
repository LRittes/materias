package apresentacao;

import dados.*;
import negocio.*;

public class Principal{

    public static void main(String[] args) {
        
        Negocio sistema = new Negocio();
        Cliente c1 = new Cliente();
        Endereco e1 = new Endereco();
        Veiculo v1 = new Veiculo();
        Data d1 = new Data();

        c1.setNome("Joao");
        c1.setCpf("25695810382");

        e1.setRua("Joao colin");
        e1.setNumero(1020);
        e1.setBairro("Gloria");
        e1.setCidade("Joinville");
        e1.setEstado("SC");
        e1.setCep("1230932");
        c1.setEndereco(e1);

        v1.setModelo("Sedan");
        v1.setMarca("Fiat Pulse");
        v1.setAno(2022);
        d1.setDia(20);
        d1.setMes(05);
        d1.setAno(2022);
        v1.setData(d1);
        c1.setVeiculoAlugado(v1);

        sistema.setContClientes();
        sistema.setClientes(c1);

        sistema.setContAlugados();
        sistema.setVeiculos(v1);

        System.out.println(sistema.toString());

    }
}