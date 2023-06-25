package interf;

import negocio.Sistema;
import dados.Pessoa;

import java.util.Scanner;

public class Main {
    private Sistema sist = new Sistema();
    private Scanner leitor = new Scanner(System.in);

    public static void main(String[] args) {
        Main lista = new Main();
        int op = 0;

        while (op != -1) {
            System.out.println("Digite 1 para continuar e -1 para encerrar");
            System.out.println("--> ");

            op = lista.leitor.nextInt();

            switch (op) {
                case 1:
                    lista.adicionarPessoa();
                    lista.exibirPessoas();
                    break;
                case -1:
                    break;
            }
        }
    }

    public Pessoa newPessoa() {
        System.out.println("Nome: ");
        String name = leitor.nextLine();
        name = leitor.nextLine();

        System.out.println("Idade: ");
        int idade = leitor.nextInt();

        System.out.println("CPF: ");
        int cpf = leitor.nextInt();

        System.out.println("Cidade: ");
        String cidade = leitor.nextLine();
        cidade = leitor.nextLine();

        Pessoa p = new Pessoa();
        p.setNome(name);
        p.setIdade(idade);
        p.setCpf(cpf);
        p.setCid(cidade);

        return p;
    }

    public void adicionarPessoa() {
        System.out.println("Novo Contato");
        Pessoa pessoa = newPessoa();
        sist.adicionarPessoa(pessoa);
    }

    public void exibirPessoas() {
        sist.mapPessoas().forEach(
                (ch, li) -> {
                    if (ch == 1) {
                        System.out.println("1 até 12: crianças;");
                    } else if (ch == 2) {
                        System.out.println("\n13 at ́e 18: adolescentes;");
                    } else if (ch == 2) {
                        System.out.println("\n19 at ́e 25: jovens;");
                    } else if (ch == 2) {
                        System.out.println("\n26 at ́e 59: adultos;");
                    } else if (ch == 2) {
                        System.out.println("\n60 ou mais: idosos;");
                    }
                    System.out.println("\n");
                    li.forEach(pessoa -> {
                        System.out.println(pessoa.toString());
                    });
                });
    }
}
