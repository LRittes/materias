package dados;

public class ContaBancaria {
    
    private String cpf;
    private float saldo;

    public ContaBancaria(){
        this.saldo = 0;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float sacar(float valor){
        
        if(saldo - valor >= 0){
            saldo -= valor;
        }else{
            System.out.println("Nao tem saldo suficiente!!");
        }

        return valor;
    }

    public String gerarExtrato(){
        return "Seu saldo é: R$"+ saldo;
    }

    public String toString() {
        return "Cpf=" + cpf;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContaBancaria other = (ContaBancaria) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        if (Float.floatToIntBits(saldo) != Float.floatToIntBits(other.saldo))
            return false;
        return true;
    }

    
    

}