package dados;

public class ContaCorrente extends ContaBancaria{

    public boolean depositar(float valor){
        setSaldo(getSaldo() + valor);
        return true;
    }

}