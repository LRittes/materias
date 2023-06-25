package POO6;

public class NúmerosPrimos extends Gerador {

    @Override
    public void gerar(int qnt) {
        for (int i = 2; i < qnt; i++) {
            int s = 0;
            for (int j = 1; j < i; j++) {
                if (i % j == 0)
                    s++;
            }
            if (s == 1)
                this.sequencia.add(i);
        }
    }

}
