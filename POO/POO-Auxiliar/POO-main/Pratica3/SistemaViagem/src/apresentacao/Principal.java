package src.apresentacao;

import java.util.Scanner;

import src.dados.Cidade;
import src.dados.Reserva;
import src.dados.Cliente;
import src.negocio.ReservaPassagem;

public class Principal{

    static Scanner s = new Scanner(System.in);
    private static ReservaPassagem sistema = new ReservaPassagem();

    public static Reserva fazerReserva(){

        Reserva reserva = new Reserva();

        System.out.println("Digite o numero da reserva: ");
        reserva.setNumReserva(Integer.valueOf(s.nextLine()));
        System.out.println("Digite a data do voo: ");
        reserva.setDataVoo(s.nextLine());
        System.out.println("Digite a hora do voo: ");
        reserva.setHoraVoo(s.nextLine());
        System.out.println("Digite o preco: ");
        reserva.setPreco(Integer.valueOf(s.nextLine()));
        System.out.println("Digite a classe do voo: ");
        reserva.setClasseVoo(s.nextLine());
        System.out.println("Digite a poltrona: ");
        reserva.setPoltrona(Integer.valueOf(s.nextLine()));
        
        System.out.println("Digite a cidade de origem: ");
        reserva.getOrigem().setNome(s.nextLine());
        System.out.println("Digite o estado da cidade de origem: ");
        reserva.getOrigem().setEstado(s.nextLine());  
        System.out.println("Digite a cidade de destino: ");
        reserva.getDestino().setNome(s.nextLine());
        System.out.println("Digite o estado da cidade de destino: ");
        reserva.getDestino().setEstado(s.nextLine());


        return reserva;
    }

    public static void cadastrarCliente(){
        
        Cliente cliente = new Cliente();

        System.out.println("Digite o CPF: ");
        cliente.setCpf(Long.valueOf(s.nextLine()));
        System.out.println("Digite o nome do cliente: ");
        cliente.setNome(s.nextLine());
        System.out.println("Digite endereco do cliente: ");
        cliente.setEndereco(s.nextLine());
        System.out.println("Digite o telefone do cliente: ");
        cliente.setTelefone(Long.valueOf(s.nextLine()));

        sistema.cadastrarCliente(cliente);

    }

    public static void cadastrarCidade(){

        Cidade cidade = new Cidade();

        System.out.println("Digite o nome da cidade: ");
        cidade.setNome(s.nextLine());
        System.out.println("Digite o estado da cidade: ");
        cidade.setEstado(s.nextLine());
        
        sistema.cadastrarCidade(cidade);

    }
    
    public static void exibirMenu(){

        System.out.println(("\n--------MENU--------"));
        System.out.println(("1 - Cadastrar Cliente; "));
        System.out.println(("2 - Cadastrar Cidade; "));
        System.out.println(("3 - Reservar Ida; "));
        System.out.println(("4 - Reservar Volta; "));
        System.out.println(("5 - Mostrar Reservas;"));
        System.out.println(("6 - Mostrar Clientes; "));
        System.out.println(("7 - Mostrar Cidades; "));
        System.out.println("0 - Sair; ");

    }

    public static void exibirClientes(){
        
        Cliente [] clientes = sistema.mostrarClientes();

        if(clientes == null){
            System.out.println("Não há clientes cadastros!!!");
        }else{
            for(int i = 0 ; i < clientes.length ; i++){
                if(clientes[i] == null){
                    break;
                }else{
                    System.out.println("\n----Cliente "+(i+1)+"----");
                    System.out.println(clientes[i].toString());
                }
            }
        }

    }

    public static void exibirCidades(){

        Cidade [] cidades = sistema.mostrarCidades();

        if(cidades == null){
            System.out.println("Não há cidades cadastradas!!");
        }else{
            for(int i = 0 ; i < cidades.length ; i++){
                if(cidades[i] == null){
                    break;
                }else{
                    System.out.println("\n----Cidade"+(i+1)+"----");
                    System.out.println(cidades[i].toString());
                }
            }
        }

    }

    public static Cliente escolheCliente(){
        
        exibirClientes();
        System.out.println("Digite o numero do cliente que deseja cadastrar:");
        int numCliente = Integer.parseInt(s.nextLine());
        Cliente [] clientes = sistema.getListaDeClientes();

        return clientes[numCliente - 1];
    }

    public static void mostrarReserva(long cpfCliente){

        Reserva [] reservas = sistema.mostrarReservas(cpfCliente);

        if(reservas == null){
            System.out.println("\nNão há reserva!!\n");
        }else{
            for(int i = 0 ; i < reservas.length ; i++){
                if(reservas[i] == null){
                    break;
                }else{
                System.out.println("\n----Reserva "+(i+1)+"----");
                System.out.println(reservas[i].toString());
                }
            }
        }
    }

    public static Reserva escolheIda(Cliente cliente){ 

        Reserva [] reservasCliente = cliente.getReservas();

        mostrarReserva(cliente.getCpf());
        System.out.println("Escolha a reserva que voce quer cadastrar a volta: ");
        int numVolta = Integer.valueOf(s.nextLine());

        return reservasCliente[numVolta - 1];
    }

    public static void main(String[] args) {

        int escolha = 0;
        
        do{

            exibirMenu();
            System.out.println("Escolha uma opção do menu: ");
            escolha = Integer.valueOf(s.nextLine());

            switch(escolha){

                case 1:
                    cadastrarCliente();
                    break;

                case 2:
                    cadastrarCidade();
                    break;

                case 3:

                    Cliente clienteIda = escolheCliente();
                    Reserva reservaIda = fazerReserva();

                    sistema.reservarIda(clienteIda, reservaIda);
                    
                    break;

                case 4:

                    Cliente clienteVolta = escolheCliente();
                    Reserva reservaIda2 = escolheIda(clienteVolta);
                    Reserva reservaVolta = fazerReserva();

                    sistema.reservarVolta(clienteVolta, reservaIda2, reservaVolta);
                
                    break;

                case 5:
                    System.out.println("Digite o Cpf do que voce quer mostrar as reservas: ");
                    long cpfCliente = Long.valueOf(s.nextLine());

                    mostrarReserva(cpfCliente);
                    break;

                    case 6:
                        exibirClientes();
                        break;

                    case 7:
                        exibirCidades();
                        break;

            }


        }while(escolha != 0);
        
    }

}