package dados;

public abstract class Fibonacci extends Gerador{

    public void gerar(int n){
        for(int i = 1 ; i <= n ; i++){
            getSequencia().add(fib(i));
        }
    }
    private int fib(int n){
        if(n< 2){
            return n;
        }else{
            return fib(n-1) + fib(n-2);
        }
    }

}