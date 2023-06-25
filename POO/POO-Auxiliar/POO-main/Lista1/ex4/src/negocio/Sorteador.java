package negocio;

import dados.Pessoa;
import java.util.Random;
import java.util.ArrayList;

public class Sorteador {

    ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
    Random gerador = new Random();

    public Pessoa sortearProximo(){
        return this.pessoas.remove(gerador.nextInt(pessoas.size()));
    }

    public void setPessoa(Pessoa pessoa){
        this.pessoas.add(pessoa);
    }
    public ArrayList<Pessoa> getPessoas(){
        return pessoas;
    }

}
