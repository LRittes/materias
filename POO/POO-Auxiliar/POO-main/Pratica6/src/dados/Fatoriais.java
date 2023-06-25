package dados;

public abstract class Fatoriais extends Gerador{

    public void gerar(int n) {
        for(int i=1; i<n; i++){
            sequencia.add(this.fatorial(i));
        }
    }
    private int fatorial(int n){
        if(!(n > 1)){
            return n;
        }else {
            return n * fatorial(n - 1);
        }
    }
    
}