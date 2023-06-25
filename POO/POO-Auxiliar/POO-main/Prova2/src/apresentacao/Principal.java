package apresentacao;

import dados.*;
import negocio.GerenciadorFormasGeometricas;

import java.util.List;
import java.util.Scanner;

public class Principal {

    public static Scanner sc = new Scanner(System.in);
    public static GerenciadorFormasGeometricas sistema = new GerenciadorFormasGeometricas();

    public static void menu(){
        System.out.println("-----MENU-----");
        System.out.println("1- Criar Quadrado;");
        System.out.println("2- Criar Retangulo;");
        System.out.println("3- Criar Circulo;");
        System.out.println("4- Criar Triangulo;");
        System.out.println("5- Mostrar formas;");
        System.out.println("6- Mostrar Perimetros e Areas:");
        System.out.println("0- Sair;");
    }

    public static void calculaPerimetroEArea(List<FormaGeometrica> formasGeometricas){

        for(int i = 0 ; i < formasGeometricas.size(); i++){
            System.out.println("Tipo: " + formasGeometricas.get(i).getClass());
            System.out.println("Perimetro: " + formasGeometricas.get(i).CalculaPerimetro());
            System.out.println("Area: " + formasGeometricas.get(i).CalculaArea());
        }

    }


    public static void main(String[] args) {

        int op = 0;

        do {

            menu();
            System.out.println("Digite a opcao desejada: ");
            op = Integer.valueOf(sc.nextLine());


            switch(op){
                case 1:
                    System.out.println("Digite x no plano cartesiano: ");
                    int xQ = Integer.valueOf(sc.nextLine());
                    System.out.println("Digite y no plano cartesiano: ");
                    int yQ = Integer.valueOf(sc.nextLine());
                    System.out.println("Digite o tamanho do lado: ");
                    int tamLado = Integer.valueOf(sc.nextLine());
                    Quadrado quadrado = new Quadrado(xQ,yQ, tamLado);

                    sistema.inserirFormaGeomatrica(quadrado);
                    break;

                case 2:
                    System.out.println("Digite x no plano cartesiano: ");
                    int xR = Integer.valueOf(sc.nextLine());
                    System.out.println("Digite y no plano cartesiano: ");
                    int yR= Integer.valueOf(sc.nextLine());
                    System.out.println("Digite o tamanho da Base: ");
                    int tamBase = Integer.valueOf(sc.nextLine());
                    System.out.println("Digite o tamanho do Altura: ");
                    int tamAltura = Integer.valueOf(sc.nextLine());
                    Retangulo retangulo = new Retangulo(xR,yR, tamBase, tamAltura);

                    sistema.inserirFormaGeomatrica(retangulo);
                    break;

                case 3:
                    System.out.println("Digite o tamanho do raio: ");
                    int raio = Integer.valueOf(sc.nextLine());
                    Circulo circulo = new Circulo(raio);

                    sistema.inserirFormaGeomatrica(circulo);
                    break;

                case 4:
                    System.out.println("Digite x no plano cartesiano: ");
                    int xT = Integer.valueOf(sc.nextLine());
                    System.out.println("Digite y no plano cartesiano: ");
                    int yT = Integer.valueOf(sc.nextLine());
                    System.out.println("Digite o tamanho do lado: ");
                    int tamLadoT = Integer.valueOf(sc.nextLine());
                    TrianguloEquilatero triangulo = new TrianguloEquilatero(xT,yT, tamLadoT);

                    sistema.inserirFormaGeomatrica(triangulo);
                    break;

                case 5:

                    System.out.println(sistema.getListaPoligonos().toString());

                    break;

                case 6:

                    calculaPerimetroEArea(sistema.getListaPoligonos());
                    break;
            }

        }while(op != 0);


    }

}
