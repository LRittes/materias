package lista1.exer4;

import java.util.List;
import java.lang.Math;

public class Sorteador {
    private List<Pessoa> pessoas;

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void addPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    public Pessoa sorteaProximo() {
        int n = (int) (Math.random() * pessoas.size());
        Pessoa pessoa = pessoas.get(n);
        pessoas.remove(n);
        return pessoa;
    }
}
