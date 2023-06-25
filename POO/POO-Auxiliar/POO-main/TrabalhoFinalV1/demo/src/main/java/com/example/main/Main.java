package com.example.main;

import java.util.Scanner;
import java.util.Arrays;

import com.example.negocio.Sistema;

public class Main {

    private static Sistema sistema = new Sistema();
    private static Scanner scanner = new Scanner(System.in);

    public static void menu(){
        System.out.println("\n--------MENU--------");
        System.out.println("1- Buscar Personagem; ");
        System.out.println("2- Buscar Episodio; ");
        System.out.println("3- Buscar Morte Personagem; ");
        System.out.println("4- Frase Aleatoria; ");
        System.out.println("5- Salvar Personagem; ");
        System.out.println("6- Excluir Personagem Salvo; ");
        System.out.println("7- Mostrar Pesonagens Salvos; ");
        System.out.println("8- Salvar Episodio; ");
        System.out.println("9- Excluir Episodio Salvo; ");
        System.out.println("10- Mostrar Episodios Salvos; ");
        System.out.println("0- Sair; ");
    }

    public static String juntaNome(String nome){

        String nomeJunto = nome.replaceAll(" ", "+");
        
        return nomeJunto;
    }


    public static void main(String[] args){

        int op = 0;

        do{

            menu();
            System.out.print("Digite o numero escolhido do menu: ");
            op = Integer.valueOf(scanner.nextLine());

            switch (op) {
                case 1:
                    System.out.print("Digite o nome do Personagem: ");
                    String nome = scanner.nextLine();

                    if(sistema.getCharacter(nome).length == 0){
                        System.out.println("\nPersonagem Nao Encontrado!!");
                    }else{
                        System.out.println("\n" + Arrays.toString(sistema.getCharacter(juntaNome(nome))) + "\n");
                    }
                    break;

                case 2:
                    System.out.print("Digite o numero do Episodio: ");
                    Integer ep = Integer.valueOf(scanner.nextLine());

                    if(sistema.getEpisode(ep).length == 0){
                        System.out.println("\nEpisodio Nao Encontrado!!");
                    }else{
                        System.out.println("\n" + Arrays.toString(sistema.getEpisode(ep)) + "\n");
                    }
                    break;

                case 3:
                    System.out.print("Digite o nome do Personagem: ");
                    String nomeMorte = scanner.nextLine();

                    if(sistema.getDeath(nomeMorte).length == 0){
                        System.out.println("\nPersonagem Nao Encontrado!!");
                    }else{
                        System.out.println("\n" + Arrays.toString(sistema.getDeath(juntaNome(nomeMorte)))+ "\n");
                    }
                    break;

                case 4:
                    System.out.println("\n" + Arrays.toString(sistema.getQuote())+"\n");
                    break;

                case 5:
                    System.out.print("Digite o nome do Personagem que voce quer salvar: ");
                    String nomeSalvar = scanner.nextLine();

                    sistema.adicionarCharacter(sistema.getCharacter(nomeSalvar)[0]);
                    System.out.println("\nPersonagem Salvo!!\n");
                    break;
                
                case 6:
                    System.out.print("Digite o nome do Personagem que deseja excluir; ");
                    String nomeExcluir = scanner.nextLine();

                    sistema.removerCharacter(sistema.getCharacter(nomeExcluir)[0]);
                    System.out.println("\nPersonagem Removido!!\n");
                    break;
                
                case 7:
                    if(sistema.getListaCharacter().isEmpty()){
                        System.out.println("\nNenhum Personagem Salvo!!");
                    }else{
                        System.out.print("\n"+sistema.getListaCharacter());
                    }
                    break;

                case 8:
                    System.out.print("Digite o numero do Episodio que voce quer salvar: ");
                    Integer epSalvar = Integer.valueOf(scanner.nextLine());

                    sistema.adicionarEpisode(sistema.getEpisode(epSalvar)[0]);
                    System.out.println("\nEpisodio Salvo!!\n");
                    break;

                case 9:
                    System.out.print("Digite o numero do Episodio que voce deseja remover: ");
                    int epExcluir = Integer.valueOf(scanner.nextLine());

                    sistema.removerEpisode(sistema.getEpisode(epExcluir)[0]);
                    System.out.println("\nEpisodio Removido!!\n");
                    break;

                case 10:
                    if(sistema.getListaEpisode().isEmpty()){
                        System.out.println("\nNenhum Episodio Salvo!!");
                    }else{
                        System.out.println("\n"+sistema.getListaEpisode());
                    }
                    break;

            }
        } while (op != 0);

    }
    
}
