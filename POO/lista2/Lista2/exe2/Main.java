import java.util.List;
import java.util.ArrayList;

import java.util.Scanner;

public class Main {
    private Scanner sca = new Scanner(System.in);

    public static void main(String[] args) {
        Main sist = new Main();
        Matriz<Integer> mat = new Matriz<Integer>(5, 5);

        // Testes
        int option = 0;
        while (option != -1) {
            System.out.println("Matriz 5x5");
            System.out.println(" 1 - criar uma Matriz 5x5");
            System.out.println("Menu: ");
            System.out.println("-1 - fecha o programa");
            System.out.println(">");

            option = Integer.valueOf(sist.sca.nextLine());

            switch (option) {
                case 1:
                    sist.novaMatriz(mat);
                    int menor = sist.menorValoreDosQuadrantes(mat);
                    System.out.println("Menor Valor: " + menor);

                    break;
                case -1:
                    break;
            }

        }
    }

    public Matriz<Integer> novaMatriz(Matriz<Integer> mat) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println("[" + i + "][" + j + "]");
                System.out.println(">");
                int num = Integer.valueOf(sca.nextLine());
                mat.set(num, i, j);
            }
        }
        return mat;
    }

    public List<Integer> retornaQuadrante(Matriz<Integer> A, Matriz.Quadrante quadrante) {
        List<Integer> q = A.getElementosQuadrante(quadrante);

        return q;
    }

    public int menorValoreDosQuadrantes(Matriz<Integer> A) {
        List<Integer> menoresValores = new ArrayList<Integer>();
        menoresValores.add(menorValor(retornaQuadrante(A, Matriz.Quadrante.PRIMEIRO)));
        menoresValores.add(menorValor(retornaQuadrante(A, Matriz.Quadrante.SEGUNDO)));
        menoresValores.add(menorValor(retornaQuadrante(A, Matriz.Quadrante.TERCEIRO)));
        menoresValores.add(menorValor(retornaQuadrante(A, Matriz.Quadrante.QUARTO)));

        for (Integer i : menoresValores) {
            System.out.println(i);
        }

        return menorValor(menoresValores);
    }

    public int menorValor(List<Integer> q) {
        int men = 0, contador = 0;
        for (Integer i : q) {
            if (contador == 0) {
                men = i;
                contador++;
            } else {
                if (i < men) {
                    men = i;
                }
            }
        }
        return men;
    }

}
