package apresentacao;

import java.util.Random;

import dados.MDC;
import dados.Mod;
import dados.Multiplicacao;
import dados.Soma;

public class Apresentacao {
    public static void main(String[] args) {
        Soma soma = new Soma();
        Random nRandom = new Random();
        Mod mod = new Mod();
        Multiplicacao mult = new Multiplicacao();
        MDC mdc = new MDC();
        int n1 = nRandom.nextInt(100);
        int n2 = nRandom.nextInt(100);

        System.out.println("Multiplicacao: " + n1 + " * " + n2 + " = " + mult.executar(n1, n2));
        System.out.println("Mdc: " + n1 + " % " + n2 + " = " + mdc.executar(n1, n2));
        System.out.println("Soma: " + n1 + " + " + n2 + " = " + soma.executar(n1, n2));
        System.out.println("Mod entre: " + n1 + " e " + n2 + " = " + mod.executar(n1, n2));
    }
}