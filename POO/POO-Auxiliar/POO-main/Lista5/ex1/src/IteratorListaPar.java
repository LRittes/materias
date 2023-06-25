public class IteratorListaPar<T> implements Iterator<T>{
    private Lista<T> lista;
    private int atual = -2;
    private int finalLista;

    public IteratorListaPar(Lista<T> lista){
        this.lista = lista;;
        this.finalLista = lista.getTam()-2;
    }

    public boolean hasNext(){
        if(this.atual < this.finalLista){
            return true;
        }else{
            return false;
        }
    }

    public T next(){
        this.atual += 2;
        return this.lista.get(atual);
    }

    public void reset(){
        this.atual = -2;
    }

}