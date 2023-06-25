package apresentacao;

import java.util.Scanner;

import dados.Circulo;
import dados.FormaGeometrica;
import dados.Quadrado;
import dados.Retangulo;
import dados.TrianguloEquilatero;
import negocio.GerenciadorFormasGeometricas;

public class Principal {
    public static void main(String[] args) {
        int option = -1;
        Principal system = new Principal();
        Scanner scan = new Scanner(System.in);
        GerenciadorFormasGeometricas genFormas = new GerenciadorFormasGeometricas();

        while (option != 3) {
            system.showPanel();
            System.out.println("Escolha: ");
            option = scan.nextInt();
            System.out.println("\n");
            switch (option) {
                case 1:
                    system.createForm(genFormas);
                    break;
                case 2:
                    system.showAllForms(genFormas);

                case 3:
                    break;
                default:
                    break;
            }
        }
        scan.close();
    }

    public void showPanel() {
        System.out.println("\n============ Sistema ============\n");
        System.out.println("1 - Criar forma geométrica\n");
        System.out.println("2 - Mostrar todas as formas geométricas cadastradas\n");
        System.out.println("3 - Sair\n\n");

    }

    public void createForm(GerenciadorFormasGeometricas genFormas) {
        FormaGeometrica form = new Circulo(0, 0, 0);
        Scanner scan = new Scanner(System.in);

        int chosenForm = -1;
        System.out.println("Qual forma:\n");
        System.out.println("1 - Circulo\n");
        System.out.println("2 - Quadrado\n");
        System.out.println("3 - Retangulo\n");
        System.out.println("4 - Triangulo Equilatero\n\n");
        chosenForm = scan.nextInt();
        double x;
        double y;
        double ladoA;
        double ladoB;

        switch (chosenForm) {
            case 1:
                System.out.println("Raio: ");
                double raio = scan.nextDouble();
                System.out.println("Posição em x: ");
                x = scan.nextDouble();

                System.out.println("Posição em y: ");
                y = scan.nextDouble();

                form = new Circulo(x, y, raio);

                break;
            case 2:
                System.out.println("Lados: ");
                ladoA = scan.nextDouble();
                System.out.println("Posição em x: ");
                x = scan.nextDouble();
                System.out.println("Posição em y: ");
                y = scan.nextDouble();

                form = new Quadrado(x, y, ladoA);

                break;
            case 3:
                System.out.println("Posição em x: ");
                x = scan.nextDouble();
                System.out.println("Posição em y: ");
                y = scan.nextDouble();
                System.out.println("Base: ");
                ladoA = scan.nextDouble();
                System.out.println("Altura: ");
                ladoB = scan.nextDouble();

                form = new Retangulo(x, y, ladoA, ladoB);

                break;

            case 4:
                System.out.println("Posição em x: ");
                x = scan.nextDouble();
                System.out.println("Posição em y: ");
                y = scan.nextDouble();
                System.out.println("Lado: ");
                ladoA = scan.nextDouble();

                form = new TrianguloEquilatero(x, y, ladoA);

                break;
            default:
                scan.close();
                break;
        }
        genFormas.addForms(form);
    }

    public void showAllForms(GerenciadorFormasGeometricas genFormas) {

        for (int i = 0; i < genFormas.getListaForm().size(); i++) {
            this.detailsForm(genFormas.getListaForm().get(i));
        }
    }

    public void detailsForm(FormaGeometrica f) {
        System.out.println(f.toString());
    }
}
