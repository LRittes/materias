package dados;

public class ContaSalario extends ContaBancaria{
    
    private String cnpjEmpresa;

    public boolean depositar(float valor, String cnpj){
        
        if(cnpj == this.cnpjEmpresa){
            setSaldo(getSaldo()+valor);
            return true;
        }

        return false;
    }

    public String gerarExtrato(){
        return "O saldo da empressa de CNPJ "+cnpjEmpresa+"é: R$"+getSaldo();
    }

    @Override
    public String toString() {
        return "ContaSalario:\n"+super.toString()+"CnpjEmpresa="+cnpjEmpresa;
    }

    

}