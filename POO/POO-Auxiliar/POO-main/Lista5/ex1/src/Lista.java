public class Lista<T>{
    private T[] lista;
    private int tam;

    public Lista(final int tam){
        this.tam = tam;
        this.lista = (T[]) new Object[this.tam];
    }

    public T get(final int i){
        return this.lista[i];
    }

    public int getTam(){
        return tam;
    }
    public T[] getLista() {
        return lista;
    }
    public void set(int i, T add){
        this.lista[i] = add;
    }
    public void setLista(final T[] lista) {
        this.lista = lista;
    }
    public void setTam(final int tam) {
        this.tam = tam;
    }

    
    
}