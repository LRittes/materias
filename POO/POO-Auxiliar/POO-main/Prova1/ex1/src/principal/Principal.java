package principal;

import java.util.Scanner;

import dados.Cliente;
import dados.ClienteOuro;
import negocio.Negocio;

public class Principal{
    
	public static Negocio negocio = new Negocio();
    public static Scanner s = new Scanner(System.in);

    public static void menu(){
        System.out.println("----Menu----");
        System.out.println("1- Cadastrar Cliente;");
        System.out.println("2- Cadastrar Cliente Ouro;");
        System.out.println("3- Mostrar Total de Mensalidades;");
        System.out.println("4- Mostrar Total de Beneficios;");
        System.out.println("5- Mostrar Nome dos Clientes;");
        System.out.println("0- Sair;");
    }

    public static void cadastraCliente(){
        
        Cliente c = new Cliente();

        System.out.print("Digite o nome do cliente: ");
        c.setNome(s.nextLine());
        System.out.print("Digite a mensalidade do cliente: ");
        c.setMensalidade(Float.valueOf(s.nextLine()));
        System.out.print("Digite o beneficio do cliente: ");
        c.setBeneficio(Float.valueOf(s.nextLine()));

        negocio.adicionaCliente(c);
    }

    public static void cadastraClienteOuro(){
        
        ClienteOuro c = new ClienteOuro();

        System.out.print("Digite o nome do cliente: ");
        c.setNome(s.nextLine());
        System.out.print("Digite a mensalidade do cliente: ");
        c.setMensalidade(Float.valueOf(s.nextLine()));
        System.out.print("Digite o beneficio do cliente: ");
        c.setBeneficio(Float.valueOf(s.nextLine()));
        System.out.print("Digite a quantidade de dependentes do cliente: ");
        c.setQuantDependentes(Integer.valueOf(s.nextLine()));

        negocio.adicionaClienteOuro(c);
    }

    public static void mostraTotalMensalidades(){
        System.out.println("O total das mensalidades é " + negocio.totalMensalidades());
    }

    public static void mostraTotalBeneficios(){
        System.out.println("O total dos beneficios é "+ negocio.totalBeneficios());
    }

    public static void mostraNomeClientesOuro(){
        System.out.println(negocio.listaNomeClientesOuro().toString());
    }

    public static void main(String[] args) {
        
        int opcao = 0;

        do{
            
            menu();
            System.out.print("Digite a opcao que voce quer: ");
            opcao = Integer.valueOf(s.nextLine());

            switch (opcao) {
                
                case 1:
                    cadastraCliente();
                    break;

                case 2:
                    cadastraClienteOuro();
                    break;

                case 3:
                    mostraTotalMensalidades();
                    break;

                case 4:
                    mostraTotalBeneficios();
                    break;

                case 5:
                    mostraNomeClientesOuro();
                    break;
                
            }

        } while (opcao != 0);

    }

}