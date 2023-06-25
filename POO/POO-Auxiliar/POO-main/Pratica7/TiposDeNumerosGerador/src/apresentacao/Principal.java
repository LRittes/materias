package apresentacao;

import dados.Abundantes;
import dados.Naturais;
import dados.Fatoriais;
import dados.Fibonacci;
import dados.NumerosPrimos;
import dados.Perfeitos;
import dados.Quadrados;


import java.util.Scanner;

public class Principal{
    public static void main(String[] args) {
        
        try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Digite um valor: ");
                int n = Integer.parseInt(scanner.nextLine());

                System.out.println("Naturais: ");
                Naturais naturais = new Naturais() {};
                naturais.gerar(n);
                System.out.println(naturais.getSequencia());
                System.out.println("Numero sorteado:" + naturais.sortear());
                System.out.println("Somatorio: " + naturais.somatorio());
                System.out.println("Media Aritmetica: " + naturais.mediaAritmetica());
                System.out.println("Media Geometrica: " + naturais.mediaGeometrica());
                System.out.println("Variancia: " + naturais.variancia());
                System.out.println("Desvio Padrao: " + naturais.desvioPadrao());
                System.out.println("Amplitude: " + naturais.amplitude());


                System.out.println("Abundantes: ");
                Abundantes abundantes = new Abundantes() {};
                abundantes.gerar(n);
                System.out.println(abundantes.getSequencia());
                System.out.println("Numero sorteado:" + abundantes.sortear());
                System.out.println("Somatorio: " + abundantes.somatorio());
                System.out.println("Media Aritmetica: " + abundantes.mediaAritmetica());
                System.out.println("Media Geometrica: " + abundantes.mediaGeometrica());
                System.out.println("Variancia: " + abundantes.variancia());
                System.out.println("Desvio Padrao: " + abundantes.desvioPadrao());
                System.out.println("Amplitude: " + abundantes.amplitude());

                System.out.println("Fibonacci: ");
                Fibonacci fibonacci = new Fibonacci() {};
                fibonacci.gerar(n);
                System.out.println(fibonacci.getSequencia());
                System.out.println("Numero sorteado:" + fibonacci.sortear());
                System.out.println("Somatorio: " + fibonacci.somatorio());
                System.out.println("Media Aritmetica: " + fibonacci.mediaAritmetica());
                System.out.println("Media Geometrica: " + fibonacci.mediaGeometrica());
                System.out.println("Variancia: " + fibonacci.variancia());
                System.out.println("Desvio Padrao: " + fibonacci.desvioPadrao());
                System.out.println("Amplitude: " + fibonacci.amplitude());

                System.out.println("Primos: ");
                NumerosPrimos primos = new NumerosPrimos() {};
                primos.gerar(n);
                System.out.println(primos.getSequencia());
                System.out.println("Numero sorteado:" + primos.sortear());
                System.out.println("Somatorio: " + primos.somatorio());
                System.out.println("Media Aritmetica: " + primos.mediaAritmetica());
                System.out.println("Media Geometrica: " + primos.mediaGeometrica());
                System.out.println("Variancia: " + primos.variancia());
                System.out.println("Desvio Padrao: " + primos.desvioPadrao());
                System.out.println("Amplitude: " + primos.amplitude());

                System.out.println("Fatorial: ");
                Fatoriais fatoriais = new Fatoriais() {};
                fatoriais.gerar(n);
                System.out.println(fatoriais.getSequencia());
                System.out.println("Numero sorteado:" + fatoriais.sortear());
                System.out.println("Somatorio: " + fatoriais.somatorio());
                System.out.println("Media Aritmetica: " + fatoriais.mediaAritmetica());
                System.out.println("Media Geometrica: " + fatoriais.mediaGeometrica());
                System.out.println("Variancia: " + fatoriais.variancia());
                System.out.println("Desvio Padrao: " + fatoriais.desvioPadrao());
                System.out.println("Amplitude: " + fatoriais.amplitude());

                System.out.println("Quadrados: ");
                Quadrados quadrados = new Quadrados();
                quadrados.gerar(n);
                System.out.println(quadrados.getSequencia());
                System.out.println("Numero sorteado:" + quadrados.sortear());
                System.out.println("Somatorio: " + quadrados.somatorio());
                System.out.println("Media Aritmetica: " + quadrados.mediaAritmetica());
                System.out.println("Media Geometrica: " + quadrados.mediaGeometrica());
                System.out.println("Variancia: " + quadrados.variancia());
                System.out.println("Desvio Padrao: " + quadrados.desvioPadrao());
                System.out.println("Amplitude: " + quadrados.amplitude());

                System.out.println("Perfeitos: ");
                Perfeitos perfeitos = new Perfeitos() {};
                perfeitos.gerar(n);
                System.out.println(perfeitos.getSequencia());
                System.out.println("Numero sorteado:" + perfeitos.sortear());
                System.out.println("Somatorio: " + perfeitos.somatorio());
                System.out.println("Media Aritmetica: " + perfeitos.mediaAritmetica());
                System.out.println("Media Geometrica: " + perfeitos.mediaGeometrica());
                System.out.println("Variancia: " + perfeitos.variancia());
                System.out.println("Desvio Padrao: " + perfeitos.desvioPadrao());
                System.out.println("Amplitude: " + perfeitos.amplitude());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            
    }

}