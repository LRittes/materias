package dados;

public abstract class Abundantes extends Gerador{
    
    public void gerar(int n){

        for(int i = 1; i <= n; i++){
            int divisores = 0;
            for(int j = 1; j < i ; j++){
                if(i % j == 0){
                    divisores += j;
                }
            }
            if(divisores > i){
                getSequencia().add(i);
            }
        }
    }

}