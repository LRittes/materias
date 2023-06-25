public class IteratorListaParImpar<T> implements Iterator<T>{
    private IteratorListaImpar<T> iteratorListaImpar;
    private IteratorListaPar<T> iteratorListaPar;

    public IteratorListaParImpar(Lista<T> lista) {
        this.iteratorListaPar = new IteratorListaPar<>(lista);
        this.iteratorListaImpar = new IteratorListaImpar<>(lista);
    }

    public boolean hasNext(){
        if(this.iteratorListaPar.hasNext()){
            return true;
        }else if(this.iteratorListaImpar.hasNext()){
            return true;
        }else{
            return false;
        }
    }

    public T next(){
        if(this.iteratorListaPar.hasNext()){
            return this.iteratorListaPar.next();
        }else{
            return this.iteratorListaImpar.next();
        }
    }

    public void reset(){
        this.iteratorListaImpar.reset();
        this.iteratorListaPar.reset();
    }


}