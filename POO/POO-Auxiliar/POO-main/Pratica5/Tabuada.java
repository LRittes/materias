import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Tabuada{

    public static List<Integer> criaTabuada(int n){

        List<Integer> tabuada = new ArrayList<Integer>();

        for(int i = 0 ; i <= 10 ; i++){
            tabuada.add(i * n);
        }

        return tabuada;
    }

    public static void main(String[] args) {
        
        Map <Integer, List<Integer>> tabuadas = new HashMap<Integer, List<Integer>>();

        for(int i = 0 ; i <=10; i++){
            tabuadas.put(i, criaTabuada(i));
        }

        tabuadas.forEach(
            (chave,tabuada) -> {
                System.out.print("Tabuada de " + chave + ": ");
                for(int x : tabuada){
                    System.out.print(x + " ");
                }
                System.out.print("\n");
            }
            
        );

    }

}