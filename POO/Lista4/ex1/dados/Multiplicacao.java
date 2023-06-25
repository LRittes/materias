package dados;

public class Multiplicacao extends Soma{
    
    public int executar(int valor1, int valor2){
        
        int multiplicacao=0;

        for(int i = 0; i < valor2 ; i++){
            multiplicacao += super.executar(valor1, valor1);
        }
        
        return multiplicacao;
    }

}