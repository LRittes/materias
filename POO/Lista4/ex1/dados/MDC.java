package dados;

public class MDC implements IOperacaoInteira {

    public int executar(int valor1, int valor2) {

        int result;
        while (valor2 != 0) {
            result = valor1 % valor2;
            valor1 = valor2;
            valor2 = result;
        }

        return valor1;
    }

}