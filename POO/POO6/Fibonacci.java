package POO6;

public class Fibonacci extends Gerador {

    @Override
    public void gerar(int qnt) {
        int n1 = 0, n2 = 1, n3, i;
        this.sequencia.add(n1);
        this.sequencia.add(n2);
        for (i = 2; i < qnt; ++i) {
            n3 = n1 + n2;
            this.sequencia.add(n3);
            n1 = n2;
            n2 = n3;
        }
    }

}
