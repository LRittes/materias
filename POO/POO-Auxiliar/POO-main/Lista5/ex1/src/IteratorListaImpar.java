public class IteratorListaImpar<T> implements Iterator<T>{

    private Lista<T> lista;
    private int atual = -1;
    private int finalLista;

    public IteratorListaImpar(Lista<T> lista) {
        this.lista = lista;
        this.finalLista = lista.getTam()-1;
    }

    @Override
    public boolean hasNext() {
        if(this.atual < this.finalLista){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public T next() {
        this.atual += 2;
        return this.lista.get(this.atual);
    }

    @Override
    public void reset() {
        this.atual = -1;
    }

    public int getAtual(){
        return atual;
    }

    
}