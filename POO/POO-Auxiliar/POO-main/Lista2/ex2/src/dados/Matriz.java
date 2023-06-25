package dados;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Matriz<T>{
    
    private int linha=0;
    private int coluna=0;
    private Map<Integer, Map<Integer, T>> mat = new HashMap<Integer, Map<Integer, T>>();

    public Matriz(int m, int n){
        this.linha = m;
        this.coluna = n;
        for(int i=0; i<linha; i++){
            Map<Integer,T> aux = new HashMap<>();
            for(int j=0; j<coluna; j++){
                aux.put(j, null);
            }
            this.mat.put(i, aux);
        }
    }

    public boolean set(T objeto, int i, int j){
        if(i < linha && j < coluna){
            this.mat.get(i).put(j, objeto);
            return true;
        }else{
            return false;
        }
    }

    T get(int i, int j){
        return this.mat.get(i).get(j);
    }

    public List<T> getLinha(int linha){
        List<T> listaLinha = new ArrayList<>();
        for(int i=0; i<coluna; i++){
            listaLinha.add(this.mat.get(linha).get(i));
        }
        return listaLinha;
    }

    public List<T> getColuna(int coluna){
        List<T> listaColuna = new ArrayList<>();
        for(int i=0; i<coluna; i++){
            listaColuna.add(this.mat.get(i).get(coluna));
        }
        return listaColuna;
    }

    public enum Quadrante{
        PRIMEIRO, SEGUNDO, TERCEIRO, QUARTO;
    }

    public List<T> getElementosQuadrante(Quadrante quadrante){
        
        List<T> elemtentosQadrante = new ArrayList<>();
        
        switch (quadrante){
            
            case PRIMEIRO:
                for(int i=0; i<Math.ceil((double)linha/2); i++){
                    for(int j=0; j<Math.ceil((double)coluna/2); j++){
                        elemtentosQadrante.add(mat.get(i).get(j));
                    }
                }
                break;

            case SEGUNDO:
                for(int i=0; i<Math.ceil((double)linha/2); i++){
                    for(int j=(int) Math.ceil((double)coluna/2); j<coluna; j++){
                        elemtentosQadrante.add(mat.get(i).get(j));
                    }
                }
                break;

            case TERCEIRO:
                for(int i = (int) Math.ceil((double)linha/2); i<linha; i++){
                    for(int j=0; j<Math.ceil((double)coluna/2); j++){
                        elemtentosQadrante.add(mat.get(i).get(j));
                    }
                }
                break;

            case QUARTO:
                for(int i=(int) Math.ceil((double)linha/2); i<linha; i++){
                    for(int j=(int) Math.ceil((double)coluna/2); j<coluna; j++){
                        elemtentosQadrante.add(mat.get(i).get(j));
                    }
                }
                break;

        }
        
        return elemtentosQadrante;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for(int i=0; i<linha; i++){
            Map<Integer, T> aux = this.mat.get(i);
            for(int j=0; j<coluna; j++){
                str.append(aux.get(j));
                str.append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }

}