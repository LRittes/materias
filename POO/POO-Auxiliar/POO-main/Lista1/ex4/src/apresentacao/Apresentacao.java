package apresentacao;

import dados.Pessoa;
import negocio.Sorteador;
import java.util.ArrayList;
import java.util.Scanner;

public class Apresentacao {
    
    private static Scanner s = new Scanner(System.in);
    private static Sorteador sorteador = new Sorteador();

    public static void adicionaPessoa(){
        sorteador.setPessoa(novaPessoa());
    }

    public static Pessoa novaPessoa(){

        Pessoa pessoa = new Pessoa();
        System.out.println("Nome: ");
        pessoa.setNome(s.nextLine());
        System.out.println("Cpf: ");
        pessoa.setCpf(Long.parseLong(s.nextLine()));

        return pessoa;
    }

    public static void mostraPessoa(){
        
        ArrayList<Pessoa> pessoas = sorteador.getPessoas();
       
        if(pessoas.size() == 0){
            System.out.println("Lista vazia");
            return;
        }
        for(Pessoa pessoa : pessoas){
            System.out.println(pessoa.toString());
        }
    }

    public static void main(String[] args) {
        
        System.out.println("Numero de pessoas: ");
        int n = Integer.valueOf(s.nextLine());

        for(int i = 0; i < n ; i++){
            System.out.println("Dados Pessoa "+(i+1)+": ");
            adicionaPessoa();
        }

        while(sorteador.getPessoas().size() != 0){
            System.out.println("\n---Sorteado---\n"+ sorteador.sortearProximo().toString()+"\n");
            System.out.println("Pessoas restantes: \n");
            mostraPessoa();
        }

    }

}
