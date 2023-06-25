package dados;

import java.util.ArrayList;
import java.util.List;

public class Pilha<T> {
    private int limite;
    private List<T> elementos = new ArrayList<T>();

    public Pilha(int limite) {
        this.limite = limite;
    }

    public int size() {
        return elementos.size();
    }

    public void insere(T objeto) {
        try {
            if (elementos.size() < limite) {
                elementos.add(objeto);
            } else {
                throw new PilhaCheiaException();
            }
        } catch (PilhaCheiaException e) {
            e.printStackTrace();
        }
    }

    public T remove() {
        T aux = null;
        try {
            if (elementos.size() > 0) {
                aux = elementos.remove(elementos.size() - 1);
            } else {
                throw new PilhaVaziaException();
            }
        } catch (PilhaVaziaException e) {
            e.printStackTrace();
        }
        return aux;
    }

}