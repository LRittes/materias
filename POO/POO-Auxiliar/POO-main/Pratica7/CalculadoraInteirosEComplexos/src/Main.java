import java.util.Random;

public class Main{
        
    public static void main(String[] args) {

        Random r = new Random();
        
        CalculadoraInteiros calculadoraInteiros = new CalculadoraInteiros();

        System.out.println(calculadoraInteiros.somar(r.nextInt(100), r.nextInt(100)));
        System.out.println(calculadoraInteiros.subtrair(r.nextInt(100), r.nextInt(100)));

        CalculadoraComplexos calculadoraComplexos = new CalculadoraComplexos();

        System.out.println(calculadoraComplexos.somar(new Complexo(r.nextInt(100),r.nextInt(100)), new Complexo(r.nextInt(100),r.nextInt(100))));
        System.out.println(calculadoraComplexos.subtrair(new Complexo(r.nextInt(100),r.nextInt(100)), new Complexo(r.nextInt(100),r.nextInt(100))));

    }

}