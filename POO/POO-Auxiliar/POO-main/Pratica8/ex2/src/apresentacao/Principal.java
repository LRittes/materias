package apresentacao;

import java.util.List;
import java.util.Scanner;

import dados.Contato;
import negocio.ListaTelefonica;

public class Principal {
    
    public static ListaTelefonica listaTelefonica = new ListaTelefonica();
    public static Scanner s = new Scanner(System.in);

    public static void menu(){
        System.out.println("-----MENU-----");
        System.out.println("1- Adicionar Contato;");
        System.out.println("2- Remover Contato;");
        System.out.println("3- Exibir Contatos;");
        System.out.println("0- Sair;");
    }

    public static Contato criaContato(){
        Contato contato = new Contato();

        System.out.print("Digite o nome: ");
        contato.setNome(s.nextLine());
        System.out.print("Digite o telefone: ");
        contato.setTelefone(s.nextLine());

        return contato;
    }

    public static void removeContato(){
        System.out.print("Digite o nome do contato que voce quer remover: ");
        String nomeRemover = s.nextLine();
        List<Contato> listaRemove = listaTelefonica.buscarContatos(nomeRemover.toUpperCase().charAt(0));
        for(int i = 0 ; i < listaRemove.size(); i++){
            if(nomeRemover == listaRemove.get(i).getNome()){
                listaTelefonica.removerContato(listaRemove.get(i));
                System.out.println("ok");
            }
        }
    }

    public static void main(String[] args) {
     
        int opcao = 0;

        do {

            menu();
            System.out.print("Digite a opcao que voce quer: ");
            opcao = Integer.valueOf(s.nextLine());

            switch (opcao) {
                
                case 1:
                    listaTelefonica.adicionarContato(criaContato());
                    System.out.println("Contato Adicionado!!!");
                    break;

                case 2:
                    removeContato();
                    System.out.println("Contato Removido!!!");
                    break;

                case 3:
                    System.out.println(listaTelefonica.listarContatos().toString());
            }
            
        } while (opcao != 0);

    }

}