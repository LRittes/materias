package apresentacao;

import java.util.List;
import java.util.Scanner;

import dados.Contato;
import negocio.ListaTelefonica;

public class Principal {
    
    private static ListaTelefonica sistema = new ListaTelefonica();
    private static Scanner s = new Scanner(System.in);

    public static void menu(){

        System.out.println("\n--------MENU--------");
        System.out.println("1 - Adicionar contato; ");
        System.out.println("2 - Remover contato; ");
        System.out.println("3 - Exibir contatos; ");
        System.out.println("4 - Exibir contato escolhido; ");
        System.out.println("0 - Sair;");

    }

    public static Contato novoContato(){

        Contato contato = new Contato();

        System.out.println("Digite o nome do contato:");
        contato.setNome(s.nextLine());
        System.out.println("Digite o telefone do contato: ");
        contato.setTelefone(s.nextLine());

        return contato;
    }

    public static void adicionarContato(Contato contato){
        sistema.adicionarContato(contato);
    }

    public static void removerContato(){

        System.out.print("Digite o nome  da pessoa que voce deseja remover o contato: ");

        String nomeRemover = s.nextLine();
        char letra = nomeRemover.toUpperCase().charAt(0);
        List<Contato> contatosTemp = sistema.buscarContato(letra);

        for(int i = 0 ; i < contatosTemp.size() ; i++){
            if(contatosTemp.get(i).getNome().equals(nomeRemover) == true){
                sistema.removerContato(contatosTemp.get(i));
            }
        }

    }

    public static void exibirContatos(){

        System.out.println(sistema.toString());

    }

    public static void exibirContatoEscolhido(){

        System.out.print("Digite o nome  da pessoa que voce deseja visualizar o contato: ");
        String nomeVisualizar = s.nextLine();
        char letra = nomeVisualizar.toUpperCase().charAt(0);
        List<Contato> contatosTemp = sistema.buscarContato(letra);

        for(int i = 0 ; i < contatosTemp.size() ; i++){
            if(contatosTemp.get(i).getNome().equals(nomeVisualizar) == true){
               System.out.print(contatosTemp.get(i).toString()); 
            }
        }

    }

    public static void main(String[] args) {
        
        int opcao = 0;

        do{

            menu();
            System.out.print("Digite o numero da opcao que voce quer: ");
            opcao = Integer.valueOf(s.nextLine());

            switch (opcao) {
                
                case 1:
                    adicionarContato(novoContato());
                    break;

                case 2:
                    removerContato();
                    break;

                case 3:
                    exibirContatos();
                    break;

                case 4:
                    exibirContatoEscolhido();
                    break;
            
            
            }

        }while(opcao != 0);
        
        
    }

}