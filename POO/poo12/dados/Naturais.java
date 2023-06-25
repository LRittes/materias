package dados;

public class Naturais extends Gerador {

    @Override
    public void gerar(int qnt) {
        for (int i = 0; i < qnt; i++)
            this.sequencia.add(i);
    }

}
