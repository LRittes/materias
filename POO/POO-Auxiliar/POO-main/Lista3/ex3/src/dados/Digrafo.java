package dados;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Digrafo {

    private Map<Integer, List<Integer>> matrizAdjacencia = new HashMap<>();
    private int vertices = 0;

    public void adicionarVertice(){
        vertices += 1;
        List<Integer> adiciona = new ArrayList<>();
        
        for(int i = 0 ; i < vertices ;i++){
            adiciona.add(0);
        }

        matrizAdjacencia.put(vertices-1, adiciona);
        for(int i = 0; i < vertices ; i++){
            this.matrizAdjacencia.get(i).add(0);
        }

    }

    public void adicionarAresta(int origem, int destino){
        if(this.matrizAdjacencia.get(origem).get(destino) == 0){
            this.matrizAdjacencia.get(origem).add(destino , 1);
        }
    }


    public String toString(){
        String str = "  ";
        for(int i=0; i<vertices; i++){
            str += i + " ";
        }
        str+="\n";
        for(int i=0; i<vertices; i++){
            str += i + " ";
            for(int j=0; j<vertices; j++){
                str += this.matrizAdjacencia.get(i).get(j) + " ";
            }
            str += "\n";
        }
        return str;
    }

}
