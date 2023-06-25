package POO6;

public class Fatoriais extends Gerador {

    @Override
    public void gerar(int qnt) {
        int fatorial = 1;

        for (int j = 1; j <= qnt; j++) {
            fatorial = fatorial * j;
        }
        this.sequencia.add(fatorial);
    }

}
