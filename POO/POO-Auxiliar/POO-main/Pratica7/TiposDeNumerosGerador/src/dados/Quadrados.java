package dados;

public class Quadrados extends Gerador{

    public void gerar(int n){
        for(int i = 1; i <= n ; i++){
            getSequencia().add(i*i);
        }
    }

}