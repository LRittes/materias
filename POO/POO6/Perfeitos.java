package POO6;

public class Perfeitos extends Gerador {

    @Override
    public void gerar(int qnt) {

        for (int c = 0; c < qnt; c++) {

            int sum = 1;
            for (int i = 2; i < c; i++) {
                if (c % i == 0) {
                    sum += i;
                }

            }

            if (sum == c)
                this.sequencia.add(c);
        }
    }

}
