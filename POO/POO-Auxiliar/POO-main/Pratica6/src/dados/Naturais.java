package dados;

public abstract class Naturais extends Gerador{

    public void gerar(int n){
        for(int i = 0; i <= n ; i++){
            getSequencia().add(i);
        }
    }

}