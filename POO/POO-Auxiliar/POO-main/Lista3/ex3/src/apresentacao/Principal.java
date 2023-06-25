package apresentacao;

import dados.Digrafo;
import dados.Grafo;

public class Principal{
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        for(int i=0; i<5;i++){
            grafo.adicionarVertice();
        }
        grafo.adicionarAresta(0,1);
        grafo.adicionarAresta(0,4);
        grafo.adicionarAresta(1,2);
        grafo.adicionarAresta(1,3);
        grafo.adicionarAresta(1,4);
        grafo.adicionarAresta(2,3);
        grafo.adicionarAresta(2,4);

        System.out.println("dados.Grafo: \n");
        System.out.println(grafo.toString());

        Digrafo digrafo = new Digrafo();
        for(int i=0; i<5;i++){
            digrafo.adicionarVertice();
        }
        digrafo.adicionarAresta(0,1);
        digrafo.adicionarAresta(0,2);
        digrafo.adicionarAresta(1,1);
        digrafo.adicionarAresta(1,3);
        digrafo.adicionarAresta(2,0);
        digrafo.adicionarAresta(2,1);
        digrafo.adicionarAresta(2,3);
        digrafo.adicionarAresta(3,4);
        digrafo.adicionarAresta(4,1);

        System.out.println("dados.Digrafo: \n");
        System.out.println(digrafo.toString());

    }
}
