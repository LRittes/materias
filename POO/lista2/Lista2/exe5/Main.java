
import java.util.Scanner;

public class Main {
    private Turma turma = new Turma();
    private Scanner leitor = new Scanner(System.in);

    public static void main(String[] args) {
        Main sistema = new Main();
        int opcao = 0;

        while (opcao != -1) {
            System.out.println("Digite 1 para continuar e -1 para sair");

            opcao = sistema.leitor.nextInt();

            switch (opcao) {
                case 1:
                    sistema.adicionarAluno();
                    sistema.exibirAlunos();
                    break;
                case -1:
                    sistema.exibirEquipes();
                    break;
            }
        }
    }

    public Aluno novoAluno() {
        System.out.println("Nome: ");
        String nome = leitor.nextLine();
        nome = leitor.nextLine();

        System.out.println("Idade: ");
        int idade = leitor.nextInt();

        double[] notas = new double[5];
        for (int i = 0; i < 5; i++) {
            System.out.println("Nota " + (i + 1) + " :");
            double nota = leitor.nextDouble();
            notas[i] = nota;
        }

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setIdade(idade);
        aluno.setNotas(notas);

        return aluno;

    }

    public void adicionarAluno() {
        System.out.println("Novo aluno- ");
        Aluno aluno = novoAluno();
        turma.adicionarAluno(aluno);
    }

    public void exibirAlunos() {
        for (Aluno a : turma.retornarAlunos()) {
            System.out.println(a.toString());
        }
    }

    public void exibirEquipes() {
        for (Equipe<Aluno> e : turma.separarEmEquipes()) {
            System.out.println(e.getNome());
            for (Object a : e.getEquipe()) {
                System.out.println(a.toString());
            }
        }
    }
}
