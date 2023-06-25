package dados;

public abstract class NumerosPrimos extends Gerador{
    
    public void gerar(int n){
        for(int i = 2 ; i <= n ; i++){
            int quantDivisores = 0;
            for(int primo : this.sequencia){
                if(i % primo == 0){
                    quantDivisores++;
                }
            }
            if(!(quantDivisores > 0)){
                getSequencia().add(i);
            }
        }
    }

}