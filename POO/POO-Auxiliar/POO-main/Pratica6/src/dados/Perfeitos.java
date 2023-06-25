package dados;

public abstract class Perfeitos extends Gerador{
    
    public void gerar(int n) {
        for(int i=1; i<n; i++){
            int somadiv = 0;
            for(int j=1; j<i; j++){
                if(i%j == 0){
                    somadiv += j;
                }
            }
            if(i == somadiv){
                sequencia.add(i);
            }

        }
    }

}