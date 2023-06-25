package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dados.Pessoa;

public class Principal{
    
    private static Scanner s = new Scanner(System.in);
    private static List<Pessoa> pessoas = new ArrayList<Pessoa>();

    public static void mostraPessoas(){
        System.out.println("\n1 até 12: crianças;");
        for(Pessoa exibe : pessoas){
            if(exibe.getIdade() >= 1 && exibe.getIdade() <= 12){
                System.out.println(exibe);
            }
        }
        System.out.println("\n13 até 18: adolescentes;");
        for(Pessoa exibe : pessoas){
            if(exibe.getIdade() > 12 && exibe.getIdade() <= 18){
                System.out.println(exibe);
            }
        }
        System.out.println("\n19 até 25: jovens;");
        for(Pessoa exibe : pessoas){
            if(exibe.getIdade() > 18 && exibe.getIdade() <= 25){
                System.out.println(exibe);
            }
        }
        System.out.println("\n26 até 59: adultos;");
        for(Pessoa exibe : pessoas){
            if(exibe.getIdade() > 25 && exibe.getIdade() <= 59){
                System.out.println(exibe);
            }
        }
        System.out.println("\n60 ou mais: idosos;");
        for(Pessoa exibe : pessoas){
            if(exibe.getIdade() > 59){
                System.out.println(exibe);
            }
        }
    }

    public static Pessoa novaPessoa(){
        Pessoa nova = new Pessoa();
        System.out.print("Nome: ");
        nova.setNome(s.nextLine());
        System.out.print("Cidade: ");
        nova.setCidade(s.nextLine());
        System.out.print("CPF: ");
        nova.setCpf(s.nextLine());
        System.out.print("Idade: ");
        nova.setIdade(Integer.parseInt(s.nextLine()));
        nova.setIdPessoa(pessoas.size()+1);
        return nova;
    }

    public static void adicionarPessoa(){
        pessoas.add(novaPessoa());
    }

    public static void main(String[] args) {
        
        int op = 0;

        do{
            System.out.println("Adicionar pessoas");
            adicionarPessoa();
            mostraPessoas();
            System.out.println("\nDigite 1 para continuar");
            System.out.println("Digite -1 para parar\n");
            op = Integer.parseInt(s.nextLine());
        }while(op != -1);

    }

}
