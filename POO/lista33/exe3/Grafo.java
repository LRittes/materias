package exe3;

public class Grafo extends Digrafo {

    @Override
    public void addAresta(int origem, int destino) {
        matrizAdjacial.get(origem).set(destino, 1);
    }

}
