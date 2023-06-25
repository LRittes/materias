package POO6;

public class Abundantes extends Gerador {

    @Override
    public void gerar(int qnt) {
        for (int i = 0; i <= qnt; i++) {
            int soma, cont;

            soma = 0;

            for (cont = 1; cont <= qnt; cont++) {
                if (qnt % cont == 0) {
                    soma = soma + cont;
                }
            }

            if (soma > qnt)
                this.sequencia.add(i);
        }
    }

}
