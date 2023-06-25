package exe3;

import java.util.ArrayList;
import java.util.List;

public abstract class Digrafo {
    protected List<List<Integer>> matrizAdjacial = new ArrayList<List<Integer>>();

    public void addVertice() {
        int sizeLine = matrizAdjacial.size();

        matrizAdjacial.add(new ArrayList<Integer>());
        for (int linha = 0; linha < sizeLine; linha++) {
            matrizAdjacial.get(linha).add(0);
        }
        for (int linha = 0; linha < sizeLine + 1; linha++) {
            matrizAdjacial.get(sizeLine).add(0);
        }
    };

    public abstract void addAresta(int origem, int destino);

    @Override
    public String toString() {
        int idx = 0;
        for (List<Integer> linha : matrizAdjacial) {
            System.out.print(idx + ": ");
            for (int col : linha) {
                System.out.print(col + " ");
            }
            System.out.println("\n");
            idx++;
        }
        return "";
    }

}
