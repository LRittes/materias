public class Main{
    public static void main(String[] args) {
        Lista<Character> lista = new Lista<>(8);
        lista.set(0, 'A');
        lista.set(1, 'B');
        lista.set(2, 'C');
        lista.set(3, 'D');
        lista.set(4, 'E');
        lista.set(5, 'F');
        lista.set(6, 'G');
        lista.set(7, 'H');

        IteratorListaParImpar<Character> iteratorListaParImpar = new IteratorListaParImpar(lista);

        while(iteratorListaParImpar.hasNext()){
            System.out.print(iteratorListaParImpar.next() + "\t");
        }
    }
}