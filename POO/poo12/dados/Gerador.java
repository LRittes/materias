package dados;

import java.util.List;

public abstract class Gerador {
    protected List<Integer> sequencia;

    public List<Integer> getSequencia() {
        return sequencia;
    }

    public void setSequencia(List<Integer> sequencia) {
        this.sequencia = sequencia;
    }

    public abstract void gerar(int qnt);
}
